package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes de NumberStats - Generics com Bounded Type Parameters")
class NumberStatsTest {

    private NumberStats stats;

    @BeforeEach
    void setUp() {
        stats = new NumberStats();
    }

    @Test
    @DisplayName("deve calcular a soma de uma lista de inteiros")
    void should_calculate_sum_of_integers() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        double result = stats.sum(numbers);
        assertEquals(15.0, result, 0.0001);
    }

    @Test
    @DisplayName("deve calcular a soma de uma lista de doubles")
    void should_calculate_sum_of_doubles() {
        List<Double> numbers = List.of(1.5, 2.5, 3.0);
        double result = stats.sum(numbers);
        assertEquals(7.0, result, 0.0001);
    }

    @Test
    @DisplayName("deve calcular a média de uma lista de números")
    void should_calculate_average_of_numbers() {
        List<Integer> numbers = List.of(10, 20, 30, 40);
        double result = stats.average(numbers);
        assertEquals(25.0, result, 0.0001);
    }

    @Test
    @DisplayName("deve encontrar o maior valor em uma lista de números")
    void should_find_max_value() {
        List<Double> numbers = List.of(3.14, 2.71, 9.99, 1.41);
        double result = stats.max(numbers);
        assertEquals(9.99, result, 0.0001);
    }

    @Test
    @DisplayName("deve retornar zero ao somar uma lista vazia")
    void should_return_zero_for_empty_sum() {
        List<Integer> empty = List.of();
        double result = stats.sum(empty);
        assertEquals(0.0, result, 0.0001);
    }
}
