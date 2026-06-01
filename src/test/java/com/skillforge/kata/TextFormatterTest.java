package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("String API: Text Blocks e Formatação")
class TextFormatterTest {

    private TextFormatter subject;

    @BeforeEach
    void setUp() {
        subject = new TextFormatter();
    }

    @Test
    @DisplayName("buildJsonUser deve gerar JSON formatado a partir de Text Block")
    void should_build_json_user_from_text_block() {
        String json = subject.buildJsonUser("Ana", 30);
        assertTrue(json.contains("\"nome\": \"Ana\""), "JSON deve conter o nome");
        assertTrue(json.contains("\"idade\": 30"), "JSON deve conter a idade");
        assertTrue(json.contains("{"));
        assertTrue(json.contains("}"));
        assertTrue(json.lines().count() >= 3, "JSON deve ter múltiplas linhas");
    }

    @Test
    @DisplayName("indentText deve indentar cada linha com N espaços")
    void should_indent_each_line() {
        String texto = "linha1\nlinha2";
        String resultado = subject.indentText(texto, 4);
        assertTrue(resultado.contains("    linha1"));
        assertTrue(resultado.contains("    linha2"));
    }

    @Test
    @DisplayName("formatGreeting deve retornar saudação formatada")
    void should_format_greeting() {
        assertEquals("Olá, Maria!", subject.formatGreeting("Maria"));
        assertEquals("Olá, João!", subject.formatGreeting("João"));
    }

    @Test
    @DisplayName("countLines deve contar linhas de um text block")
    void should_count_lines_in_text_block() {
        String bloco = """
                primeira
                segunda
                terceira
                """;
        assertEquals(3, subject.countLines(bloco));
    }

    @Test
    @DisplayName("countLines deve retornar 1 para linha única")
    void should_count_single_line() {
        assertEquals(1, subject.countLines("apenas uma linha"));
    }
}
