package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BlockingIoService - Virtual Threads para I/O Bloqueante")
class BlockingIoServiceTest {

    private BlockingIoService service;

    @BeforeEach
    void setUp() {
        service = new BlockingIoService();
    }

    @Test
    @DisplayName("deve simular uma chamada bloqueante e retornar resultado")
    void should_simulate_blocking_call_and_return_result() throws Exception {
        String result = service.fetch("endpoint-1", Duration.ofMillis(50));
        assertNotNull(result);
        assertTrue(result.contains("endpoint-1"));
    }

    @Test
    @DisplayName("deve executar múltiplas chamadas em paralelo usando virtual threads")
    void should_execute_multiple_blocking_calls_in_parallel() throws Exception {
        List<String> endpoints = List.of("a", "b", "c", "d", "e");
        Duration delay = Duration.ofMillis(200);

        Instant start = Instant.now();
        List<String> results = service.fetchAll(endpoints, delay);
        Duration elapsed = Duration.between(start, Instant.now());

        assertEquals(5, results.size());
        // If sequential, would take ~1000ms. Parallel virtual threads should finish well under 800ms.
        assertTrue(elapsed.toMillis() < 800,
                "Execução paralela esperada, mas levou " + elapsed.toMillis() + "ms");
    }

    @Test
    @DisplayName("deve usar virtual threads ao executar fetchAll")
    void should_use_virtual_threads() throws Exception {
        List<String> threadNames = service.fetchAllReportingThreads(List.of("x", "y", "z"));
        assertEquals(3, threadNames.size());
        // Virtual threads typically contain 'VirtualThread' in toString or are named accordingly
        assertTrue(threadNames.stream().anyMatch(n -> n.toLowerCase().contains("virtual")),
                "Esperado pelo menos uma virtual thread em: " + threadNames);
    }

    @Test
    @DisplayName("deve escalar para muitas chamadas simultâneas")
    void should_scale_to_many_concurrent_calls() throws Exception {
        List<String> endpoints = java.util.stream.IntStream.range(0, 500)
                .mapToObj(i -> "ep-" + i)
                .toList();

        Instant start = Instant.now();
        List<String> results = service.fetchAll(endpoints, Duration.ofMillis(100));
        Duration elapsed = Duration.between(start, Instant.now());

        assertEquals(500, results.size());
        assertTrue(elapsed.toMillis() < 3000,
                "500 chamadas concorrentes devem terminar rápido com virtual threads, mas levaram "
                        + elapsed.toMillis() + "ms");
    }

    @Test
    @DisplayName("deve propagar exceções de chamadas que falharam")
    void should_propagate_exceptions_from_failing_calls() {
        assertThrows(Exception.class, () -> service.fetch(null, Duration.ofMillis(10)));
    }
}
