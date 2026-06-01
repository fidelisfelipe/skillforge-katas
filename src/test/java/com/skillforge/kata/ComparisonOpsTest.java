package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Operadores de comparação")
class ComparisonOpsTest {

    private ComparisonOps subject;

    @BeforeEach
    void setUp() {
        subject = new ComparisonOps();
    }

    @Test
    @DisplayName("isGreater deve verificar se a é maior que b")
    void is_greater_should_compare_correctly() {
        assertTrue(subject.isGreater(5, 3));
        assertFalse(subject.isGreater(3, 5));
        assertFalse(subject.isGreater(4, 4));
    }

    @Test
    @DisplayName("isLessOrEqual deve verificar menor ou igual")
    void is_less_or_equal_should_compare_correctly() {
        assertTrue(subject.isLessOrEqual(3, 5));
        assertTrue(subject.isLessOrEqual(5, 5));
        assertFalse(subject.isLessOrEqual(6, 5));
    }

    @Test
    @DisplayName("areEqual deve detectar igualdade")
    void are_equal_should_detect_equality() {
        assertTrue(subject.areEqual(7, 7));
        assertFalse(subject.areEqual(7, 8));
    }

    @Test
    @DisplayName("areDifferent deve detectar diferença")
    void are_different_should_detect_difference() {
        assertTrue(subject.areDifferent(7, 8));
        assertFalse(subject.areDifferent(7, 7));
    }

    @Test
    @DisplayName("inRange deve validar intervalo inclusivo")
    void in_range_should_validate_inclusive_range() {
        assertTrue(subject.inRange(5, 1, 10));
        assertTrue(subject.inRange(1, 1, 10));
        assertTrue(subject.inRange(10, 1, 10));
        assertFalse(subject.inRange(0, 1, 10));
        assertFalse(subject.inRange(11, 1, 10));
    }
}
