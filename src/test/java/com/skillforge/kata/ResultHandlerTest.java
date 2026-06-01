package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Sealed Types: Switch Exaustivo de Resultados")
class ResultHandlerTest {

    private ResultHandler handler;

    @BeforeEach
    void setUp() {
        handler = new ResultHandler();
    }

    @Test
    @DisplayName("deve tratar resultado de sucesso retornando o valor")
    void should_handle_success_result() {
        ResultHandler.Result<String> success = new ResultHandler.Success<>("OK");
        String message = handler.describe(success);
        assertNotNull(message);
        assertTrue(message.contains("OK"), "Mensagem deveria conter o valor: " + message);
        assertTrue(message.toLowerCase().contains("success") || message.toLowerCase().contains("sucesso"),
                "Mensagem deveria indicar sucesso: " + message);
    }

    @Test
    @DisplayName("deve tratar resultado de falha retornando a mensagem de erro")
    void should_handle_failure_result() {
        ResultHandler.Result<String> failure = new ResultHandler.Failure<>("Erro de conexão");
        String message = handler.describe(failure);
        assertNotNull(message);
        assertTrue(message.contains("Erro de conexão"), "Mensagem deveria conter o erro: " + message);
        assertTrue(message.toLowerCase().contains("fail") || message.toLowerCase().contains("falha") || message.toLowerCase().contains("erro"),
                "Mensagem deveria indicar falha: " + message);
    }

    @Test
    @DisplayName("deve tratar resultado pendente retornando indicação de pendência")
    void should_handle_pending_result() {
        ResultHandler.Result<String> pending = new ResultHandler.Pending<>();
        String message = handler.describe(pending);
        assertNotNull(message);
        assertTrue(message.toLowerCase().contains("pend"),
                "Mensagem deveria indicar pendência: " + message);
    }

    @Test
    @DisplayName("deve extrair valor ou retornar default usando switch exaustivo")
    void should_extract_value_or_default() {
        assertEquals("hello", handler.getOrDefault(new ResultHandler.Success<>("hello"), "default"));
        assertEquals("default", handler.getOrDefault(new ResultHandler.Failure<>("err"), "default"));
        assertEquals("default", handler.getOrDefault(new ResultHandler.Pending<>(), "default"));
    }

    @Test
    @DisplayName("Result deve ser sealed permitindo apenas Success, Failure e Pending")
    void result_should_be_sealed_with_three_subtypes() {
        Class<?> resultClass = ResultHandler.Result.class;
        assertTrue(resultClass.isSealed(), "Result deveria ser sealed");
        Class<?>[] permitted = resultClass.getPermittedSubclasses();
        assertNotNull(permitted);
        assertEquals(3, permitted.length, "Result deveria permitir exatamente 3 subtipos");
    }
}
