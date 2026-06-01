package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Operadores aritméticos e precedência")
class ArithmeticOpsTest {

    private ArithmeticOps subject;

    @BeforeEach
    void setUp() {
        subject = new ArithmeticOps();
    }

    @Test
    @DisplayName("sum deve somar dois inteiros")
    void sum_should_add_two_integers() {
        assertEquals(7, subject.sum(3, 4));
        assertEquals(0, subject.sum(-5, 5));
        assertEquals(-3, subject.sum(-1, -2));
    }

    @Test
    @DisplayName("remainder deve retornar o resto da divisão")
    void remainder_should_return_modulo() {
        assertEquals(1, subject.remainder(10, 3));
        assertEquals(0, subject.remainder(20, 4));
        assertEquals(2, subject.remainder(7, 5));
    }

    @Test
    @DisplayName("integerDivision deve truncar resultado")
    void integer_division_should_truncate() {
        assertEquals(3, subject.integerDivision(10, 3));
        assertEquals(5, subject.integerDivision(20, 4));
        assertEquals(0, subject.integerDivision(2, 5));
    }

    @Test
    @DisplayName("mixedDivision deve promover para double")
    void mixed_division_should_promote_to_double() {
        assertEquals(2.5, subject.mixedDivision(5, 2), 1e-9);
        assertEquals(3.3333333, subject.mixedDivision(10, 3), 1e-6);
    }

    @Test
    @DisplayName("precedenceExample deve respeitar precedência")
    void precedence_example_should_respect_order() {
        assertEquals(9, subject.precedenceExample());
    }
}
