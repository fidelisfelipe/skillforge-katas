package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TriFunction - Interface funcional customizada com três argumentos")
class TriFunctionTest {

    private TriFunction<Integer, Integer, Integer, Integer> sum3;
    private TriFunction<String, String, String, String> concat3;

    @BeforeEach
    void setUp() {
        sum3 = (a, b, c) -> a + b + c;
        concat3 = (a, b, c) -> a + b + c;
    }

    @Test
    @DisplayName("deve aplicar a função somando três inteiros")
    void should_apply_sum_of_three_integers() {
        assertEquals(6, sum3.apply(1, 2, 3));
        assertEquals(0, sum3.apply(0, 0, 0));
        assertEquals(-3, sum3.apply(-1, -1, -1));
    }

    @Test
    @DisplayName("deve aplicar a função concatenando três Strings")
    void should_apply_concatenation_of_three_strings() {
        assertEquals("abc", concat3.apply("a", "b", "c"));
        assertEquals("Hello World!", concat3.apply("Hello", " World", "!"));
    }

    @Test
    @DisplayName("deve permitir encadeamento usando andThen")
    void should_chain_with_andThen() {
        TriFunction<Integer, Integer, Integer, Integer> chained =
                sum3.andThen(result -> result * 2);

        assertEquals(12, chained.apply(1, 2, 3), "(1+2+3)*2 = 12");
        assertEquals(20, chained.apply(2, 3, 5));
    }

    @Test
    @DisplayName("deve encadear transformando tipo do resultado")
    void should_chain_changing_result_type() {
        TriFunction<Integer, Integer, Integer, String> chained =
                sum3.andThen(result -> "Total: " + result);

        assertEquals("Total: 10", chained.apply(2, 3, 5));
    }

    @Test
    @DisplayName("deve trabalhar com tipos heterogêneos")
    void should_work_with_heterogeneous_types() {
        TriFunction<String, Integer, Boolean, String> formatter =
                (name, age, active) -> name + "/" + age + "/" + active;

        assertEquals("Alice/30/true", formatter.apply("Alice", 30, true));
        assertEquals("Bob/25/false", formatter.apply("Bob", 25, false));
    }
}
