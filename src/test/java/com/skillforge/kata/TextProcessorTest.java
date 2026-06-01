package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("String API: Métodos Modernos de Texto")
class TextProcessorTest {

    private TextProcessor subject;

    @BeforeEach
    void setUp() {
        subject = new TextProcessor();
    }

    @Test
    @DisplayName("isBlankText deve identificar strings em branco corretamente")
    void should_detect_blank_strings() {
        assertTrue(subject.isBlankText(""));
        assertTrue(subject.isBlankText("   "));
        assertTrue(subject.isBlankText("\t\n  "));
        assertFalse(subject.isBlankText("texto"));
        assertFalse(subject.isBlankText("  a  "));
    }

    @Test
    @DisplayName("repeatText deve repetir a string N vezes")
    void should_repeat_text_n_times() {
        assertEquals("abcabcabc", subject.repeatText("abc", 3));
        assertEquals("", subject.repeatText("abc", 0));
        assertEquals("x", subject.repeatText("x", 1));
        assertEquals("---------", subject.repeatText("---", 3));
    }

    @Test
    @DisplayName("splitLines deve dividir texto em lista de linhas")
    void should_split_text_into_lines() {
        String texto = "linha1\nlinha2\nlinha3";
        List<String> linhas = subject.splitLines(texto);
        assertEquals(3, linhas.size());
        assertEquals("linha1", linhas.get(0));
        assertEquals("linha2", linhas.get(1));
        assertEquals("linha3", linhas.get(2));
    }

    @Test
    @DisplayName("stripText deve remover espaços em branco do início e fim")
    void should_strip_whitespace_from_text() {
        assertEquals("texto", subject.stripText("  texto  "));
        assertEquals("abc", subject.stripText("\t\nabc\n\t"));
        assertEquals("", subject.stripText("     "));
        assertNull(subject.stripText(null));
    }

    @Test
    @DisplayName("splitLines em uma única linha retorna lista de tamanho 1")
    void should_handle_single_line() {
        List<String> linhas = subject.splitLines("apenas uma");
        assertEquals(1, linhas.size());
        assertEquals("apenas uma", linhas.get(0));
    }
}
