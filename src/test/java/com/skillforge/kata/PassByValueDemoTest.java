package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Passagem por valor: primitivos e referências")
class PassByValueDemoTest {

    private PassByValueDemo subject;

    @BeforeEach
    void setUp() {
        subject = new PassByValueDemo();
    }

    @Test
    @DisplayName("increment em primitivo não altera variável do chamador")
    void primitive_is_passed_by_value() {
        int x = 10;
        subject.increment(x);
        assertEquals(10, x);
    }

    @Test
    @DisplayName("reatribuir array dentro do método não afeta o chamador")
    void reassigning_array_reference_does_not_propagate() {
        int[] arr = {1, 2, 3};
        subject.reassignArray(arr);
        assertArrayEquals(new int[]{1, 2, 3}, arr);
    }

    @Test
    @DisplayName("mutar conteúdo do array propaga ao chamador")
    void mutating_array_contents_propagates() {
        int[] arr = {1, 2, 3};
        subject.mutateArray(arr);
        assertEquals(42, arr[0]);
        assertEquals(2, arr[1]);
        assertEquals(3, arr[2]);
    }

    @Test
    @DisplayName("mutar StringBuilder propaga, reatribuir não propaga")
    void stringbuilder_mutation_vs_reassignment() {
        StringBuilder sb = new StringBuilder("oi");
        subject.appendExclamation(sb);
        assertEquals("oi!", sb.toString());

        subject.reassignStringBuilder(sb);
        assertEquals("oi!", sb.toString());
    }
}
