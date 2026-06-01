package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes de ExprEvaluator - Sealed Interfaces + Records ADT")
class ExprEvaluatorTest {

    private ExprEvaluator evaluator;

    @BeforeEach
    void setUp() {
        evaluator = new ExprEvaluator();
    }

    @Test
    @DisplayName("Deve avaliar literal numérico")
    void should_evaluate_literal() {
        Expr expr = new Expr.Lit(42.0);
        assertEquals(42.0, evaluator.eval(expr), 0.0001);
    }

    @Test
    @DisplayName("Deve avaliar soma de dois literais")
    void should_evaluate_addition() {
        Expr expr = new Expr.Add(new Expr.Lit(3.0), new Expr.Lit(4.0));
        assertEquals(7.0, evaluator.eval(expr), 0.0001);
    }

    @Test
    @DisplayName("Deve avaliar multiplicação")
    void should_evaluate_multiplication() {
        Expr expr = new Expr.Mul(new Expr.Lit(6.0), new Expr.Lit(7.0));
        assertEquals(42.0, evaluator.eval(expr), 0.0001);
    }

    @Test
    @DisplayName("Deve avaliar expressões aninhadas (2+3)*4")
    void should_evaluate_nested_expression() {
        Expr expr = new Expr.Mul(
            new Expr.Add(new Expr.Lit(2.0), new Expr.Lit(3.0)),
            new Expr.Lit(4.0)
        );
        assertEquals(20.0, evaluator.eval(expr), 0.0001);
    }

    @Test
    @DisplayName("Deve avaliar negação de uma expressão")
    void should_evaluate_negation() {
        Expr expr = new Expr.Neg(new Expr.Add(new Expr.Lit(1.0), new Expr.Lit(2.0)));
        assertEquals(-3.0, evaluator.eval(expr), 0.0001);
    }
}
