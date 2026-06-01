package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Operadores lógicos e curto-circuito")
class LogicalOpsTest {

    private LogicalOps subject;

    @BeforeEach
    void setUp() {
        subject = new LogicalOps();
    }

    @Test
    @DisplayName("and deve aplicar E lógico")
    void and_should_apply_logical_and() {
        assertTrue(subject.and(true, true));
        assertFalse(subject.and(true, false));
        assertFalse(subject.and(false, true));
        assertFalse(subject.and(false, false));
    }

    @Test
    @DisplayName("or deve aplicar OU lógico")
    void or_should_apply_logical_or() {
        assertTrue(subject.or(true, false));
        assertTrue(subject.or(false, true));
        assertTrue(subject.or(true, true));
        assertFalse(subject.or(false, false));
    }

    @Test
    @DisplayName("xor deve aplicar OU exclusivo")
    void xor_should_apply_exclusive_or() {
        assertTrue(subject.xor(true, false));
        assertTrue(subject.xor(false, true));
        assertFalse(subject.xor(true, true));
        assertFalse(subject.xor(false, false));
    }

    @Test
    @DisplayName("shortCircuitSafe deve tratar null sem lançar exceção")
    void short_circuit_safe_should_handle_null() {
        assertDoesNotThrow(() -> subject.shortCircuitSafe(null));
        assertFalse(subject.shortCircuitSafe(null));
        assertFalse(subject.shortCircuitSafe(0));
        assertFalse(subject.shortCircuitSafe(-3));
        assertTrue(subject.shortCircuitSafe(5));
    }

    @Test
    @DisplayName("isLeapYear deve identificar anos bissextos")
    void is_leap_year_should_identify_leap_years() {
        assertTrue(subject.isLeapYear(2000));
        assertTrue(subject.isLeapYear(2024));
        assertFalse(subject.isLeapYear(1900));
        assertFalse(subject.isLeapYear(2023));
        assertFalse(subject.isLeapYear(2100));
    }
}
