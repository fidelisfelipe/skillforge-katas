package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pipeline Assíncrono de Pedidos com CompletableFuture")
class AsyncOrderPipelineTest {

    private AsyncOrderPipeline pipeline;

    @BeforeEach
    void setUp() {
        pipeline = new AsyncOrderPipeline();
    }

    @Test
    @DisplayName("deve validar um pedido de forma assíncrona retornando true para id positivo")
    void should_validate_order_async() throws Exception {
        CompletableFuture<Boolean> future = pipeline.validateOrder(42L);
        assertNotNull(future);
        assertTrue(future.get(2, TimeUnit.SECONDS));
    }

    @Test
    @DisplayName("deve processar pedido completo encadeando validação, cobrança e envio")
    void should_process_full_pipeline() throws Exception {
        CompletableFuture<String> result = pipeline.processOrder(100L, 250.0);
        String confirmation = result.get(3, TimeUnit.SECONDS);
        assertNotNull(confirmation);
        assertTrue(confirmation.contains("100"), "Confirmação deve conter o id do pedido");
    }

    @Test
    @DisplayName("deve falhar o pipeline quando o id do pedido for inválido (negativo)")
    void should_fail_pipeline_for_invalid_order() {
        CompletableFuture<String> result = pipeline.processOrder(-1L, 50.0);
        ExecutionException ex = assertThrows(ExecutionException.class,
                () -> result.get(2, TimeUnit.SECONDS));
        assertNotNull(ex.getCause());
    }

    @Test
    @DisplayName("deve processar múltiplos pedidos em paralelo e agregar resultados")
    void should_process_multiple_orders_in_parallel() throws Exception {
        List<Long> ids = List.of(1L, 2L, 3L, 4L);
        CompletableFuture<List<String>> all = pipeline.processAll(ids, 10.0);
        List<String> results = all.get(5, TimeUnit.SECONDS);
        assertEquals(4, results.size());
        assertTrue(results.stream().allMatch(s -> s != null && !s.isBlank()));
    }

    @Test
    @DisplayName("deve aplicar timeout e retornar valor padrão quando processamento exceder o limite")
    void should_apply_timeout_with_fallback() throws Exception {
        CompletableFuture<String> result = pipeline.processWithTimeout(7L, 99.0, 1L);
        String value = result.get(3, TimeUnit.SECONDS);
        assertNotNull(value);
    }
}
