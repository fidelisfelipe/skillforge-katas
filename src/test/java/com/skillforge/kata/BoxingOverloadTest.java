package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Sobrecarga com autoboxing e varargs")
class BoxingOverloadTest {

    private BoxingOverload subject;

    @BeforeEach
    void setUp() {
        subject = new BoxingOverload();
    }

    @Test
    @DisplayName("int literal prefere int sobre long, Integer e varargs")
    void int_literal_picks_int() {
        assertEquals("int", subject.invokeWithInt());
    }

    @Test
    @DisplayName("Integer boxed prefere a sobrecarga Integer")
    void integer_picks_integer_overload() {
        assertEquals("Integer", subject.invokeWithInteger());
    }

    @Test
    @DisplayName("short sofre widening para int, não autoboxing para Short")
    void short_widens_to_int_overload() {
        assertEquals("int", subject.invokeWithShort());
    }

    @Test
    @DisplayName("dois ints só correspondem à sobrecarga varargs")
    void two_ints_pick_varargs() {
        assertEquals("varargs", subject.invokeWithTwoInts());
    }

    @Test
    @DisplayName("chamadas diretas confirmam cada sobrecarga isoladamente")
    void direct_calls_resolve_each_overload() {
        assertEquals("long", subject.pick(5L));
        assertEquals("Object", subject.pick((Object) "texto"));
        assertEquals("varargs", subject.pick(new int[]{1, 2, 3}));
    }
}
