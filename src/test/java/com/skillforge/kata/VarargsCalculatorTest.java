package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Varargs: somatórios e estatísticas")
class VarargsCalculatorTest {

    private VarargsCalculator subject;

    @BeforeEach
    void setUp() {
        subject = new VarargsCalculator();
    }

    @Test
    @DisplayName("sum soma varargs e retorna 0 quando vazio")
    void sum_handles_varargs_and_empty() {
        assertEquals(0, subject.sum());
        assertEquals(10, subject.sum(1, 2, 3, 4));
        assertEquals(-5, subject.sum(-10, 5));
    }

    @Test
    @DisplayName("average calcula a média e retorna 0.0 quando vazio")
    void average_computes_mean() {
        assertEquals(0.0, subject.average(), 0.0001);
        assertEquals(2.5, subject.average(1.0, 2.0, 3.0, 4.0), 0.0001);
        assertEquals(5.0, subject.average(5.0), 0.0001);
    }

    @Test
    @DisplayName("max combina o parâmetro obrigatório com os varargs")
    void max_uses_required_plus_varargs() {
        assertEquals(7, subject.max(7));
        assertEquals(9, subject.max(3, 1, 9, 4));
        assertEquals(10, subject.max(10, 2, 5));
    }

    @Test
    @DisplayName("join concatena com separador e retorna vazio sem partes")
    void join_concatenates_parts() {
        assertEquals("", subject.join(","));
        assertEquals("a", subject.join(",", "a"));
        assertEquals("a-b-c", subject.join("-", "a", "b", "c"));
    }

    @Test
    @DisplayName("count retorna o número de argumentos passados")
    void count_returns_number_of_arguments() {
        assertEquals(0, subject.count());
        assertEquals(3, subject.count("a", 1, true));
        assertEquals(1, subject.count((Object) null));
    }
}
