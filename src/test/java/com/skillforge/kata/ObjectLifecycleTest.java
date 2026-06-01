package com.skillforge.kata;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Inicialização: campos, construtores e blocos")
class ObjectLifecycleTest {

    private ObjectLifecycle subject;

    @BeforeEach
    void setUp() {
        subject = new ObjectLifecycle();
    }

    @Test
    @DisplayName("log contém exatamente [instance-init, constructor] após a construção")
    void log_contains_init_and_constructor_in_order() {
        assertEquals(List.of("instance-init", "constructor"), subject.getLog());
    }

    @Test
    @DisplayName("value é 3 após a execução completa da construção")
    void value_is_three_after_construction() {
        assertEquals(3, subject.getValue());
    }

    @Test
    @DisplayName("cada nova instância possui seu próprio log")
    void each_instance_has_its_own_log() {
        ObjectLifecycle other = new ObjectLifecycle();
        assertNotSame(subject.getLog(), other.getLog());
        assertEquals(subject.getLog(), other.getLog());
    }

    @Test
    @DisplayName("a primeira entrada do log é instance-init (executa antes do construtor)")
    void instance_init_runs_before_constructor() {
        assertEquals("instance-init", subject.getLog().get(0));
        assertEquals("constructor", subject.getLog().get(1));
    }
}
