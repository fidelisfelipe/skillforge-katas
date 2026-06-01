package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes do record Temperature - Compact Constructors e Validação")
class TemperatureTest {

    private Temperature roomTemp;

    @BeforeEach
    void setUp() {
        roomTemp = new Temperature(20.0, "C");
    }

    @Test
    @DisplayName("Deve criar Temperature com valores válidos")
    void should_create_valid_temperature() {
        assertEquals(20.0, roomTemp.value(), 0.0001);
        assertEquals("C", roomTemp.unit());
    }

    @Test
    @DisplayName("Deve rejeitar temperatura abaixo do zero absoluto em Celsius")
    void should_reject_below_absolute_zero_celsius() {
        assertThrows(IllegalArgumentException.class, () -> new Temperature(-300.0, "C"));
    }

    @Test
    @DisplayName("Deve rejeitar unidade inválida")
    void should_reject_invalid_unit() {
        assertThrows(IllegalArgumentException.class, () -> new Temperature(20.0, "X"));
    }

    @Test
    @DisplayName("Deve converter Celsius para Fahrenheit corretamente")
    void should_convert_celsius_to_fahrenheit() {
        Temperature fahrenheit = roomTemp.toFahrenheit();
        assertEquals(68.0, fahrenheit.value(), 0.0001);
        assertEquals("F", fahrenheit.unit());
    }

    @Test
    @DisplayName("Deve aceitar Kelvin e Fahrenheit como unidades válidas")
    void should_accept_kelvin_and_fahrenheit() {
        Temperature k = new Temperature(300.0, "K");
        Temperature f = new Temperature(100.0, "F");
        assertEquals("K", k.unit());
        assertEquals("F", f.unit());
    }
}
