package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes da hierarquia de exceções do domínio financeiro")
class PaymentExceptionTest {

    private PaymentException paymentException;

    @BeforeEach
    void setUp() {
        paymentException = new PaymentException("Falha no pagamento");
    }

    @Test
    @DisplayName("Deve ser uma exceção checked herdando de Exception")
    void should_be_checked_exception_extending_exception() {
        assertTrue(paymentException instanceof Exception,
                "PaymentException deve herdar de Exception");
        assertFalse(paymentException instanceof RuntimeException,
                "PaymentException não deve ser RuntimeException (deve ser checked)");
    }

    @Test
    @DisplayName("Deve preservar a mensagem informada no construtor")
    void should_preserve_message_from_constructor() {
        PaymentException ex = new PaymentException("Cartão recusado");
        assertEquals("Cartão recusado", ex.getMessage());
    }

    @Test
    @DisplayName("Deve aceitar construtor com mensagem e causa")
    void should_accept_message_and_cause_constructor() {
        Throwable cause = new RuntimeException("Timeout do gateway");
        PaymentException ex = new PaymentException("Erro de comunicação", cause);
        assertEquals("Erro de comunicação", ex.getMessage());
        assertSame(cause, ex.getCause());
    }

    @Test
    @DisplayName("Deve poder ser lançada e capturada como Exception")
    void should_be_throwable_and_catchable_as_exception() {
        Exception captured = assertThrows(PaymentException.class, () -> {
            throw new PaymentException("Saldo insuficiente");
        });
        assertEquals("Saldo insuficiente", captured.getMessage());
    }

    @Test
    @DisplayName("Deve permitir subclasses específicas como InsufficientFundsException")
    void should_allow_subclass_hierarchy() {
        PaymentException ex = new PaymentException("Pagamento genérico");
        assertNotNull(ex);
        // Verifica que a classe não é final permitindo extensão
        assertFalse(java.lang.reflect.Modifier.isFinal(PaymentException.class.getModifiers()),
                "PaymentException não deve ser final para permitir hierarquia");
    }
}
