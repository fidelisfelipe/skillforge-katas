package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Escopo de variáveis: bloco, método e classe")
class ScopeRulesTest {

    private ScopeRules subject;

    @BeforeEach
    void setUp() {
        subject = new ScopeRules();
    }

    @Test
    @DisplayName("counter inicia em 0 e increment retorna o valor incrementado")
    void increment_increases_counter() {
        assertEquals(0, subject.currentCounter());
        assertEquals(1, subject.increment());
        assertEquals(2, subject.increment());
        assertEquals(2, subject.currentCounter());
    }

    @Test
    @DisplayName("sumBlockScope soma de 1 até n usando variável de bloco no for")
    void sum_block_scope_returns_correct_sum() {
        assertEquals(15, subject.sumBlockScope(5));
        assertEquals(55, subject.sumBlockScope(10));
        assertEquals(0, subject.sumBlockScope(0));
    }

    @Test
    @DisplayName("shadowExample usa o parâmetro sombreado e não modifica o campo")
    void shadow_example_does_not_modify_field() {
        subject.increment();
        assertEquals(1, subject.currentCounter());
        assertEquals(20, subject.shadowExample(10));
        assertEquals(1, subject.currentCounter(), "O campo counter da instância não deve mudar");
    }

    @Test
    @DisplayName("chamadas independentes a sumBlockScope não compartilham estado")
    void sum_block_scope_is_stateless() {
        subject.sumBlockScope(100);
        assertEquals(0, subject.currentCounter());
        assertEquals(6, subject.sumBlockScope(3));
    }
}
