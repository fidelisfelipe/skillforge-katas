package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Sobrecarga de métodos: resolução pelo tipo do parâmetro")
class OverloadResolverTest {

    private OverloadResolver subject;

    @BeforeEach
    void setUp() {
        subject = new OverloadResolver();
    }

    @Test
    @DisplayName("int literal seleciona a sobrecarga de int")
    void int_literal_resolves_to_int_overload() {
        assertEquals("int", subject.describe(42));
    }

    @Test
    @DisplayName("long literal seleciona a sobrecarga de long")
    void long_literal_resolves_to_long_overload() {
        assertEquals("long", subject.describe(42L));
    }

    @Test
    @DisplayName("double literal seleciona a sobrecarga de double")
    void double_literal_resolves_to_double_overload() {
        assertEquals("double", subject.describe(3.14));
    }

    @Test
    @DisplayName("short sofre widening para int (não há sobrecarga short)")
    void short_widens_to_int() {
        short s = 5;
        assertEquals("int", subject.describe(s));
    }

    @Test
    @DisplayName("Integer e String selecionam suas sobrecargas específicas, não Object")
    void wrapper_and_string_prefer_specific_overload() {
        Integer boxed = 10;
        assertEquals("Integer", subject.describe(boxed));
        assertEquals("String", subject.describe("hello"));
    }
}
