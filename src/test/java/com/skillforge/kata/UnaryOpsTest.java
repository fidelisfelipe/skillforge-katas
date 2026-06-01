package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Operadores unários: incremento e decremento")
class UnaryOpsTest {

    private UnaryOps subject;

    @BeforeEach
    void setUp() {
        subject = new UnaryOps();
    }

    @Test
    @DisplayName("postIncrementResult deve retornar valor incrementado")
    void post_increment_result_should_return_incremented() {
        assertEquals(6, subject.postIncrementResult(5));
        assertEquals(1, subject.postIncrementResult(0));
        assertEquals(0, subject.postIncrementResult(-1));
    }

    @Test
    @DisplayName("preIncrementResult deve retornar valor incrementado")
    void pre_increment_result_should_return_incremented() {
        assertEquals(11, subject.preIncrementResult(10));
        assertEquals(1, subject.preIncrementResult(0));
    }

    @Test
    @DisplayName("postIncrementInExpression deve avaliar a++ + a corretamente")
    void post_increment_in_expression_should_be_eleven() {
        assertEquals(11, subject.postIncrementInExpression());
    }

    @Test
    @DisplayName("negate deve retornar negativo do valor")
    void negate_should_return_negative() {
        assertEquals(-5, subject.negate(5));
        assertEquals(7, subject.negate(-7));
        assertEquals(0, subject.negate(0));
    }

    @Test
    @DisplayName("not deve inverter valor booleano")
    void not_should_invert_boolean() {
        assertTrue(subject.not(false));
        assertFalse(subject.not(true));
    }
}
