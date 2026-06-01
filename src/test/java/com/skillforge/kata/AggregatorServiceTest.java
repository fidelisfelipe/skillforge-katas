package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.Callable;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("AggregatorService - Structured Concurrency com StructuredTaskScope")
class AggregatorServiceTest {

    private AggregatorService service;

    @BeforeEach
    void setUp() {
        service = new AggregatorService();
    }

    @Test
    @DisplayName("deve agregar resultados de múltiplas tarefas com sucesso")
    void should_aggregate_results_from_multiple_successful_tasks() throws Exception {
        List<Callable<String>> tasks = List.of(
                () -> { Thread.sleep(50); return "A"; },
                () -> { Thread.sleep(50); return "B"; },
                () -> { Thread.sleep(50); return "C"; }
        );

        List<String> results = service.aggregate(tasks);

        assertEquals(3, results.size());
        assertTrue(results.containsAll(List.of("A", "B", "C")));
    }

    @Test
    @DisplayName("deve executar tarefas concorrentemente, não sequencialmente")
    void should_run_tasks_concurrently() throws Exception {
        List<Callable<Integer>> tasks = List.of(
                () -> { Thread.sleep(300); return 1; },
                () -> { Thread.sleep(300); return 2; },
                () -> { Thread.sleep(300); return 3; },
                () -> { Thread.sleep(300); return 4; }
        );

        Instant start = Instant.now();
        List<Integer> results = service.aggregate(tasks);
        Duration elapsed = Duration.between(start, Instant.now());

        assertEquals(4, results.size());
        assertTrue(elapsed.toMillis() < 900,
                "Tarefas deveriam rodar em paralelo, mas levaram " + elapsed.toMillis() + "ms");
    }

    @Test
    @DisplayName("deve falhar rapidamente se qualquer subtarefa falhar (ShutdownOnFailure)")
    void should_fail_fast_when_any_subtask_fails() {
        List<Callable<String>> tasks = List.of(
                () -> { Thread.sleep(2000); return "slow"; },
                () -> { Thread.sleep(100); throw new RuntimeException("boom"); },
                () -> { Thread.sleep(2000); return "slow2"; }
        );

        Instant start = Instant.now();
        Exception ex = assertThrows(Exception.class, () -> service.aggregate(tasks));
        Duration elapsed = Duration.between(start, Instant.now());

        assertTrue(elapsed.toMillis() < 1500,
                "Deveria falhar rapidamente, mas levou " + elapsed.toMillis() + "ms");
        assertNotNull(ex);
    }

    @Test
    @DisplayName("deve retornar o primeiro resultado bem-sucedido (ShutdownOnSuccess)")
    void should_return_first_successful_result() throws Exception {
        List<Callable<String>> tasks = List.of(
                () -> { Thread.sleep(500); return "slow"; },
                () -> { Thread.sleep(50); return "fast"; },
                () -> { Thread.sleep(800); return "slowest"; }
        );

        Instant start = Instant.now();
        String result = service.firstSuccessful(tasks);
        Duration elapsed = Duration.between(start, Instant.now());

        assertEquals("fast", result);
        assertTrue(elapsed.toMillis() < 400,
                "Deveria retornar assim que o primeiro completar, mas levou " + elapsed.toMillis() + "ms");
    }

    @Test
    @DisplayName("deve lidar com lista vazia de tarefas")
    void should_handle_empty_task_list() throws Exception {
        List<String> results = service.aggregate(List.of());
        assertNotNull(results);
        assertTrue(results.isEmpty());
    }
}
