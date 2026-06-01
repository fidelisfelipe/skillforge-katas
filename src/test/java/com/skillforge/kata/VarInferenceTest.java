package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Inferência de tipo com var")
class VarInferenceTest {

    private VarInference subject;

    @BeforeEach
    void setUp() {
        subject = new VarInference();
    }

    @Test
    @DisplayName("greet concatena saudação com o nome usando var")
    void greet_concatenates_with_name() {
        assertEquals("Olá, Maria", subject.greet("Maria"));
    }

    @Test
    @DisplayName("sumList soma a lista [1..5] resultando em 15")
    void sum_list_returns_fifteen() {
        assertEquals(15, subject.sumList());
    }

    @Test
    @DisplayName("countWords retorna a quantidade de palavras separadas por espaço")
    void count_words_splits_by_space() {
        assertEquals(4, subject.countWords("java é muito legal"));
        assertEquals(1, subject.countWords("solo"));
    }

    @Test
    @DisplayName("averageOfTen retorna a média de 1 a 10 (5.5)")
    void average_of_ten_returns_5_5() {
        assertEquals(5.5, subject.averageOfTen(), 0.0001);
    }
}
