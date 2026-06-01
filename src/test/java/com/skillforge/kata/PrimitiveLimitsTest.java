package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tipos primitivos: limites e literais")
class PrimitiveLimitsTest {

    private PrimitiveLimits subject;

    @BeforeEach
    void setUp() {
        subject = new PrimitiveLimits();
    }

    @Test
    @DisplayName("maxByte retorna 127 (limite máximo de byte)")
    void max_byte_returns_127() {
        assertEquals(127, subject.maxByte());
    }

    @Test
    @DisplayName("binaryLiteral retorna 10 a partir do literal binário 0b1010")
    void binary_literal_returns_ten() {
        assertEquals(10L, subject.binaryLiteral());
    }

    @Test
    @DisplayName("underscoreMillion retorna 1_000_000")
    void underscore_million_returns_one_million() {
        assertEquals(1_000_000L, subject.underscoreMillion());
    }

    @Test
    @DisplayName("hexFifteen retorna 15 a partir do literal hexadecimal 0xF")
    void hex_fifteen_returns_fifteen() {
        assertEquals(15, subject.hexFifteen());
    }

    @Test
    @DisplayName("scientificNotation retorna 1500.0 usando notação científica")
    void scientific_notation_returns_1500() {
        assertEquals(1500.0, subject.scientificNotation(), 0.0001);
    }
}
