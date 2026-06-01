package io.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("KATA-001A: Virtual Threads — Hello Concurrency")
class ConcurrentExecutorTest {

    private ConcurrentExecutor executor;

    @BeforeEach
    void setUp() {
        executor = new ConcurrentExecutor();
    }

    @Test
    @DisplayName("Deve executar 10.000 tasks sem OutOfMemoryError")
    void shouldExecute10000TasksWithoutOOM() throws Exception {
        int taskCount = 10_000;
        List<Callable<Integer>> tasks = new ArrayList<>();

        for (int i = 0; i < taskCount; i++) {
            final int id = i;
            tasks.add(() -> {
                Thread.sleep(10);
                return id;
            });
        }

        List<Integer> results = executor.executeAll(tasks);

        assertEquals(taskCount, results.size(), "Todas as tasks devem completar");
        assertEquals(taskCount, new HashSet<>(results).size(), "Resultados devem ser únicos");
    }

    @Test
    @DisplayName("Deve completar 1.000 tasks bloqueantes em menos de 2s")
    void shouldCompleteFasterThanPlatformThreadPool() throws Exception {
        int taskCount = 1_000;
        List<Callable<Integer>> tasks = new ArrayList<>();

        for (int i = 0; i < taskCount; i++) {
            tasks.add(() -> {
                Thread.sleep(50);
                return 1;
            });
        }

        long start = System.currentTimeMillis();
        executor.executeAll(tasks);
        long duration = System.currentTimeMillis() - start;

        // 1000 tasks * 50ms com pool fixo de 10 = ~5000ms
        // Virtual Threads devem completar em ~50ms
        assertTrue(duration < 2_000,
            "Esperado < 2s, mas levou " + duration + "ms — verifique se está usando Virtual Threads");
    }

    @Test
    @DisplayName("As tasks devem rodar em Virtual Threads")
    void tasksMustRunOnVirtualThreads() throws Exception {
        AtomicInteger virtualCount = new AtomicInteger();
        AtomicInteger platformCount = new AtomicInteger();

        List<Callable<Void>> tasks = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            tasks.add(() -> {
                if (Thread.currentThread().isVirtual()) {
                    virtualCount.incrementAndGet();
                } else {
                    platformCount.incrementAndGet();
                }
                return null;
            });
        }

        executor.executeAll(tasks);

        assertEquals(0, platformCount.get(),
            "Nenhuma task deve rodar em platform thread");
        assertEquals(100, virtualCount.get(),
            "Todas as tasks devem rodar em virtual thread");
    }

    @Test
    @DisplayName("Deve retornar lista vazia para input vazio")
    void shouldHandleEmptyTaskList() throws Exception {
        List<Object> results = executor.executeAll(List.of());
        assertNotNull(results);
        assertTrue(results.isEmpty());
    }
}
