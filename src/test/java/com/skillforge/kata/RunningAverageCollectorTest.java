package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("RunningAverageCollector: implementação de Collector customizado")
class RunningAverageCollectorTest {

    private RunningAverageCollector collectorFactory;

    @BeforeEach
    void setUp() {
        collectorFactory = new RunningAverageCollector();
    }

    @Test
    @DisplayName("deve calcular a média de uma lista de doubles")
    void should_compute_average_of_doubles() {
        Collector<Double, ?, Double> collector = collectorFactory.averagingCollector();
        double avg = Stream.of(2.0, 4.0, 6.0, 8.0).collect(collector);
        assertEquals(5.0, avg, 1e-9);
    }

    @Test
    @DisplayName("deve retornar zero para um stream vazio")
    void should_return_zero_for_empty_stream() {
        Collector<Double, ?, Double> collector = collectorFactory.averagingCollector();
        double avg = Stream.<Double>empty().collect(collector);
        assertEquals(0.0, avg, 1e-9);
    }

    @Test
    @DisplayName("deve funcionar corretamente em stream paralelo (combiner)")
    void should_work_in_parallel_stream() {
        Collector<Double, ?, Double> collector = collectorFactory.averagingCollector();
        List<Double> values = List.of(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0);
        double avg = values.parallelStream().collect(collector);
        assertEquals(5.5, avg, 1e-9);
    }

    @Test
    @DisplayName("deve calcular a média de um único elemento")
    void should_compute_average_of_single_element() {
        Collector<Double, ?, Double> collector = collectorFactory.averagingCollector();
        double avg = Stream.of(42.0).collect(collector);
        assertEquals(42.0, avg, 1e-9);
    }

    @Test
    @DisplayName("deve declarar características adequadas no Collector")
    void should_declare_collector_characteristics() {
        Collector<Double, ?, Double> collector = collectorFactory.averagingCollector();
        assertNotNull(collector.characteristics());
        assertNotNull(collector.supplier());
        assertNotNull(collector.accumulator());
        assertNotNull(collector.combiner());
        assertNotNull(collector.finisher());
    }
}
