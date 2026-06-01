package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Cache Thread-safe com Capacidade Limitada e Bloqueio")
class BoundedBlockingCacheTest {

    private BoundedBlockingCache<String, Integer> cache;

    @BeforeEach
    void setUp() {
        cache = new BoundedBlockingCache<>(3);
    }

    @Test
    @DisplayName("deve armazenar e recuperar valores por chave")
    void should_put_and_get_values() throws InterruptedException {
        cache.put("a", 1);
        cache.put("b", 2);
        assertEquals(1, cache.get("a"));
        assertEquals(2, cache.get("b"));
        assertEquals(2, cache.size());
    }

    @Test
    @DisplayName("deve retornar null ao buscar chave inexistente")
    void should_return_null_for_missing_key() throws InterruptedException {
        assertNull(cache.get("missing"));
    }

    @Test
    @DisplayName("deve respeitar a capacidade máxima do cache")
    void should_respect_capacity_limit() throws InterruptedException {
        cache.put("a", 1);
        cache.put("b", 2);
        cache.put("c", 3);
        assertEquals(3, cache.size());
        cache.put("d", 4);
        assertTrue(cache.size() <= 3, "O cache não deve exceder a capacidade");
    }

    @Test
    @DisplayName("deve ser seguro para acessos concorrentes de múltiplas threads")
    void should_be_thread_safe_under_concurrent_access() throws InterruptedException {
        BoundedBlockingCache<Integer, Integer> bigCache = new BoundedBlockingCache<>(1000);
        ExecutorService pool = Executors.newFixedThreadPool(8);
        AtomicInteger errors = new AtomicInteger();
        int total = 500;
        for (int i = 0; i < total; i++) {
            final int k = i;
            pool.submit(() -> {
                try {
                    bigCache.put(k, k * 2);
                    Integer v = bigCache.get(k);
                    if (v != null && v != k * 2) errors.incrementAndGet();
                } catch (Exception e) {
                    errors.incrementAndGet();
                }
            });
        }
        pool.shutdown();
        assertTrue(pool.awaitTermination(5, TimeUnit.SECONDS));
        assertEquals(0, errors.get(), "Não deve haver corrupção de dados em acesso concorrente");
    }

    @Test
    @DisplayName("deve remover entradas e atualizar o tamanho corretamente")
    void should_remove_entries() throws InterruptedException {
        cache.put("x", 10);
        cache.put("y", 20);
        assertEquals(2, cache.size());
        Integer removed = cache.remove("x");
        assertEquals(10, removed);
        assertNull(cache.get("x"));
        assertEquals(1, cache.size());
    }
}
