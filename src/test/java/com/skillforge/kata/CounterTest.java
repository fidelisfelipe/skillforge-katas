package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Métodos estáticos vs de instância")
class CounterTest {

    @BeforeEach
    void setUp() {
        Counter.resetTotalInstances();
    }

    @Test
    @DisplayName("increment afeta apenas a instância em que é chamado")
    void instance_state_is_independent() {
        Counter a = new Counter();
        Counter b = new Counter();
        a.increment();
        a.increment();
        b.increment();
        assertEquals(2, a.getCount());
        assertEquals(1, b.getCount());
    }

    @Test
    @DisplayName("totalInstances é compartilhado pela classe")
    void static_field_is_shared() {
        new Counter();
        new Counter();
        new Counter();
        assertEquals(3, Counter.getTotalInstances());
    }

    @Test
    @DisplayName("resetTotalInstances zera o contador estático")
    void reset_static_counter() {
        new Counter();
        new Counter();
        Counter.resetTotalInstances();
        assertEquals(0, Counter.getTotalInstances());
    }

    @Test
    @DisplayName("sumOfCounts soma valores de várias instâncias via varargs")
    void sum_of_counts_uses_varargs() {
        Counter a = new Counter();
        Counter b = new Counter();
        Counter c = new Counter();
        a.increment();
        a.increment();
        b.increment();
        c.increment();
        c.increment();
        c.increment();
        assertEquals(6, Counter.sumOfCounts(a, b, c));
        assertEquals(0, Counter.sumOfCounts());
    }
}
