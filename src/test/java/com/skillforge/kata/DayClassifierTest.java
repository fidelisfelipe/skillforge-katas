package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Switch Expressions: Substituindo cadeias if-else")
class DayClassifierTest {

    private DayClassifier classifier;

    @BeforeEach
    void setUp() {
        classifier = new DayClassifier();
    }

    @Test
    @DisplayName("Deve classificar segunda a sexta como dia útil")
    void should_classify_weekdays_as_workday() {
        assertEquals("WORKDAY", classifier.classify("MONDAY"));
        assertEquals("WORKDAY", classifier.classify("TUESDAY"));
        assertEquals("WORKDAY", classifier.classify("WEDNESDAY"));
        assertEquals("WORKDAY", classifier.classify("THURSDAY"));
        assertEquals("WORKDAY", classifier.classify("FRIDAY"));
    }

    @Test
    @DisplayName("Deve classificar sábado e domingo como fim de semana")
    void should_classify_saturday_and_sunday_as_weekend() {
        assertEquals("WEEKEND", classifier.classify("SATURDAY"));
        assertEquals("WEEKEND", classifier.classify("SUNDAY"));
    }

    @Test
    @DisplayName("Deve retornar UNKNOWN para entrada inválida")
    void should_return_unknown_for_invalid_input() {
        assertEquals("UNKNOWN", classifier.classify("FUNDAY"));
        assertEquals("UNKNOWN", classifier.classify(""));
    }

    @Test
    @DisplayName("Deve calcular horas de trabalho conforme o dia")
    void should_return_work_hours_by_day() {
        assertEquals(8, classifier.workHours("MONDAY"));
        assertEquals(8, classifier.workHours("FRIDAY"));
        assertEquals(0, classifier.workHours("SUNDAY"));
        assertEquals(0, classifier.workHours("SATURDAY"));
    }
}
