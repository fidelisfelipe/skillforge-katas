package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes do record MoneyRecord - Modelo de Domínio Imutável")
class MoneyRecordTest {

    private MoneyRecord tenDollars;
    private MoneyRecord fiveDollars;

    @BeforeEach
    void setUp() {
        tenDollars = new MoneyRecord(new BigDecimal("10.00"), "USD");
        fiveDollars = new MoneyRecord(new BigDecimal("5.00"), "USD");
    }

    @Test
    @DisplayName("Deve criar record com accessors de amount e currency")
    void should_create_record_with_accessors() {
        assertEquals(new BigDecimal("10.00"), tenDollars.amount());
        assertEquals("USD", tenDollars.currency());
    }

    @Test
    @DisplayName("Deve somar dois valores monetários da mesma moeda")
    void should_add_two_money_values_with_same_currency() {
        MoneyRecord result = tenDollars.add(fiveDollars);
        assertEquals(0, new BigDecimal("15.00").compareTo(result.amount()));
        assertEquals("USD", result.currency());
    }

    @Test
    @DisplayName("Deve lançar exceção ao somar moedas diferentes")
    void should_throw_when_adding_different_currencies() {
        MoneyRecord euros = new MoneyRecord(new BigDecimal("5.00"), "EUR");
        assertThrows(IllegalArgumentException.class, () -> tenDollars.add(euros));
    }

    @Test
    @DisplayName("Deve implementar equals e hashCode baseados nos componentes")
    void should_implement_equals_and_hashcode() {
        MoneyRecord another = new MoneyRecord(new BigDecimal("10.00"), "USD");
        assertEquals(tenDollars, another);
        assertEquals(tenDollars.hashCode(), another.hashCode());
        assertNotEquals(tenDollars, fiveDollars);
    }

    @Test
    @DisplayName("Deve multiplicar valor monetário por escalar")
    void should_multiply_money_by_scalar() {
        MoneyRecord result = tenDollars.multiply(3);
        assertEquals(0, new BigDecimal("30.00").compareTo(result.amount()));
        assertEquals("USD", result.currency());
    }
}
