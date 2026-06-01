package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ciclo de vida e elegibilidade ao GC")
class GarbageCollectionDemoTest {

    private GarbageCollectionDemo subject;

    @BeforeEach
    void setUp() {
        subject = new GarbageCollectionDemo();
    }

    @Test
    @DisplayName("createAndDiscard retorna 'discarded' após anular a referência")
    void create_and_discard_returns_discarded() {
        assertEquals("discarded", subject.createAndDiscard());
    }

    @Test
    @DisplayName("sizeBeforeNull retorna 3 (tamanho guardado antes de anular a referência)")
    void size_before_null_returns_three() {
        assertEquals(3, subject.sizeBeforeNull());
    }

    @Test
    @DisplayName("reassignReference retorna 'segundo' após reatribuir a variável")
    void reassign_reference_returns_second() {
        assertEquals("segundo", subject.reassignReference());
    }

    @Test
    @DisplayName("isSameReference retorna true para variáveis que apontam ao mesmo objeto")
    void is_same_reference_returns_true() {
        assertTrue(subject.isSameReference());
    }
}
