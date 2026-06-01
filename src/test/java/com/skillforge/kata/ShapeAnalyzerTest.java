package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ShapeAnalyzer - Switch Expressions com Guarded Patterns")
class ShapeAnalyzerTest {

    private ShapeAnalyzer analyzer;

    @BeforeEach
    void setUp() {
        analyzer = new ShapeAnalyzer();
    }

    @Test
    @DisplayName("Deve classificar inteiro positivo usando guarded pattern")
    void should_classify_positive_integer() {
        String result = analyzer.classify(10);
        assertEquals("positive integer", result.toLowerCase());
    }

    @Test
    @DisplayName("Deve classificar inteiro negativo usando guarded pattern")
    void should_classify_negative_integer() {
        String result = analyzer.classify(-5);
        assertEquals("negative integer", result.toLowerCase());
    }

    @Test
    @DisplayName("Deve classificar zero usando guarded pattern")
    void should_classify_zero() {
        String result = analyzer.classify(0);
        assertEquals("zero", result.toLowerCase());
    }

    @Test
    @DisplayName("Deve classificar string vazia usando guarded pattern")
    void should_classify_empty_string() {
        String result = analyzer.classify("");
        assertEquals("empty string", result.toLowerCase());
    }

    @Test
    @DisplayName("Deve classificar string não vazia usando guarded pattern")
    void should_classify_non_empty_string() {
        String result = analyzer.classify("hello");
        assertEquals("non-empty string", result.toLowerCase());
    }
}
