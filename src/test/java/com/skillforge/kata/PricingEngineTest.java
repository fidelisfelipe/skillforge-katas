package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Switch Expressions com yield e blocos")
class PricingEngineTest {

    private PricingEngine engine;

    @BeforeEach
    void setUp() {
        engine = new PricingEngine();
    }

    @Test
    @DisplayName("Deve aplicar 10% de desconto para clientes REGULAR")
    void should_apply_regular_discount() {
        assertEquals(90.0, engine.priceFor("REGULAR", 100.0), 0.001);
    }

    @Test
    @DisplayName("Deve aplicar 20% de desconto para clientes PREMIUM")
    void should_apply_premium_discount() {
        assertEquals(80.0, engine.priceFor("PREMIUM", 100.0), 0.001);
    }

    @Test
    @DisplayName("Deve aplicar 30% de desconto para clientes VIP")
    void should_apply_vip_discount() {
        assertEquals(140.0, engine.priceFor("VIP", 200.0), 0.001);
    }

    @Test
    @DisplayName("Deve manter preço integral para clientes desconhecidos")
    void should_keep_full_price_for_unknown_tier() {
        assertEquals(150.0, engine.priceFor("GUEST", 150.0), 0.001);
        assertEquals(50.0, engine.priceFor("", 50.0), 0.001);
    }

    @Test
    @DisplayName("Deve calcular preço corretamente para diferentes valores")
    void should_compute_price_for_various_amounts() {
        assertEquals(0.0, engine.priceFor("PREMIUM", 0.0), 0.001);
        assertEquals(45.0, engine.priceFor("REGULAR", 50.0), 0.001);
        assertEquals(700.0, engine.priceFor("VIP", 1000.0), 0.001);
    }
}
