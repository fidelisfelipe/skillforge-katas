package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ShapeClassifier - Pattern Matching com instanceof sem Casting")
class ShapeClassifierTest {

    private ShapeClassifier classifier;

    @BeforeEach
    void setUp() {
        classifier = new ShapeClassifier();
    }

    @Test
    @DisplayName("Deve descrever um objeto String usando pattern matching")
    void should_describe_string_using_pattern_matching() {
        String description = classifier.describe("hello");
        assertNotNull(description);
        assertTrue(description.toLowerCase().contains("string"),
                "Descrição deve mencionar String: " + description);
        assertTrue(description.contains("5"),
                "Descrição deve conter o tamanho da string: " + description);
    }

    @Test
    @DisplayName("Deve descrever um Integer usando pattern matching")
    void should_describe_integer_using_pattern_matching() {
        String description = classifier.describe(42);
        assertNotNull(description);
        assertTrue(description.toLowerCase().contains("integer") || description.toLowerCase().contains("int"),
                "Descrição deve mencionar Integer: " + description);
        assertTrue(description.contains("42"),
                "Descrição deve conter o valor 42: " + description);
    }

    @Test
    @DisplayName("Deve descrever um Double usando pattern matching")
    void should_describe_double_using_pattern_matching() {
        String description = classifier.describe(3.14);
        assertNotNull(description);
        assertTrue(description.toLowerCase().contains("double"),
                "Descrição deve mencionar Double: " + description);
        assertTrue(description.contains("3.14"),
                "Descrição deve conter o valor 3.14: " + description);
    }

    @Test
    @DisplayName("Deve retornar descrição padrão para tipo desconhecido")
    void should_return_default_description_for_unknown_type() {
        String description = classifier.describe(new Object());
        assertNotNull(description);
        assertTrue(description.toLowerCase().contains("unknown") || description.toLowerCase().contains("desconhecido"),
                "Descrição deve indicar tipo desconhecido: " + description);
    }

    @Test
    @DisplayName("Deve tratar null de forma segura")
    void should_handle_null_safely() {
        String description = classifier.describe(null);
        assertNotNull(description);
        assertTrue(description.toLowerCase().contains("null"),
                "Descrição deve mencionar null: " + description);
    }
}
