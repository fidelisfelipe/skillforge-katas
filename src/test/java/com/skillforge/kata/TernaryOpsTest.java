package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Operador ternário")
class TernaryOpsTest {

    private TernaryOps subject;

    @BeforeEach
    void setUp() {
        subject = new TernaryOps();
    }

    @Test
    @DisplayName("max deve retornar o maior valor")
    void max_should_return_greater_value() {
        assertEquals(8, subject.max(3, 8));
        assertEquals(8, subject.max(8, 3));
        assertEquals(5, subject.max(5, 5));
        assertEquals(-1, subject.max(-1, -5));
    }

    @Test
    @DisplayName("parity deve retornar par ou ímpar")
    void parity_should_return_par_or_impar() {
        assertEquals("par", subject.parity(4));
        assertEquals("par", subject.parity(0));
        assertEquals("ímpar", subject.parity(7));
        assertEquals("ímpar", subject.parity(-3));
    }

    @Test
    @DisplayName("absoluteValue deve retornar valor absoluto")
    void absolute_value_should_return_abs() {
        assertEquals(5, subject.absoluteValue(5));
        assertEquals(5, subject.absoluteValue(-5));
        assertEquals(0, subject.absoluteValue(0));
    }

    @Test
    @DisplayName("sign deve identificar sinal do número")
    void sign_should_identify_number_sign() {
        assertEquals("positivo", subject.sign(10));
        assertEquals("negativo", subject.sign(-3));
        assertEquals("zero", subject.sign(0));
    }

    @Test
    @DisplayName("clamp deve limitar valor ao intervalo")
    void clamp_should_bound_value_to_range() {
        assertEquals(5, subject.clamp(5, 1, 10));
        assertEquals(1, subject.clamp(-3, 1, 10));
        assertEquals(10, subject.clamp(15, 1, 10));
        assertEquals(1, subject.clamp(1, 1, 10));
        assertEquals(10, subject.clamp(10, 1, 10));
    }
}
