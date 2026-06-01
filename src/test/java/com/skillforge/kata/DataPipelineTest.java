package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DataPipeline - Pipeline de composição de funções")
class DataPipelineTest {

    private DataPipeline pipeline;

    @BeforeEach
    void setUp() {
        pipeline = new DataPipeline();
    }

    @Test
    @DisplayName("deve compor duas funções aplicando primeiro a interna e depois a externa")
    void should_compose_two_functions() {
        Function<Integer, Integer> doubler = x -> x * 2;
        Function<Integer, Integer> plusTen = x -> x + 10;

        Function<Integer, Integer> composed = pipeline.compose(doubler, plusTen);

        assertEquals(20, composed.apply(5), "Deveria dobrar (10) e somar 10 = 20");
        assertEquals(10, composed.apply(0));
    }

    @Test
    @DisplayName("deve aplicar pipeline de transformação de String para Integer")
    void should_pipeline_string_to_integer() {
        Function<String, String> trim = String::trim;
        Function<String, Integer> length = String::length;

        Function<String, Integer> composed = pipeline.compose(trim, length);

        assertEquals(5, composed.apply("  hello  "));
        assertEquals(0, composed.apply("   "));
    }

    @Test
    @DisplayName("deve aplicar uma lista de funções em sequência")
    void should_apply_pipeline_of_functions() {
        List<Function<Integer, Integer>> functions = List.of(
                x -> x + 1,
                x -> x * 2,
                x -> x - 3
        );

        Integer result = pipeline.pipeline(5, functions);

        assertEquals(9, result, "(5+1)*2 - 3 = 9");
    }

    @Test
    @DisplayName("deve retornar o valor original quando a lista de funções está vazia")
    void should_return_input_when_pipeline_is_empty() {
        Integer result = pipeline.pipeline(42, List.of());
        assertEquals(42, result);
    }

    @Test
    @DisplayName("deve compor identidade sem alterar o valor")
    void should_compose_with_identity() {
        Function<Integer, Integer> identity = Function.identity();
        Function<Integer, Integer> doubler = x -> x * 2;

        Function<Integer, Integer> composed = pipeline.compose(identity, doubler);

        assertEquals(14, composed.apply(7));
    }
}
