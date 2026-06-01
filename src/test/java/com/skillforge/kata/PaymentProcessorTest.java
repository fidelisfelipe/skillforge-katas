package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Sealed Classes: Hierarquia de Tipos de Pagamento")
class PaymentProcessorTest {

    private PaymentProcessor processor;

    @BeforeEach
    void setUp() {
        processor = new PaymentProcessor();
    }

    @Test
    @DisplayName("deve processar pagamento via cartão de crédito retornando descrição correta")
    void should_process_credit_card_payment() {
        String result = processor.process(new PaymentProcessor.CreditCard("1234-5678-9012-3456", 250.00));
        assertNotNull(result);
        assertTrue(result.toLowerCase().contains("credit") || result.toLowerCase().contains("cartão") || result.toLowerCase().contains("cartao"),
                "Resultado deveria mencionar cartão de crédito: " + result);
        assertTrue(result.contains("250"), "Resultado deveria conter o valor: " + result);
    }

    @Test
    @DisplayName("deve processar pagamento via PIX retornando descrição correta")
    void should_process_pix_payment() {
        String result = processor.process(new PaymentProcessor.Pix("chave@email.com", 100.50));
        assertNotNull(result);
        assertTrue(result.toLowerCase().contains("pix"), "Resultado deveria mencionar PIX: " + result);
        assertTrue(result.contains("100"), "Resultado deveria conter o valor: " + result);
    }

    @Test
    @DisplayName("deve processar pagamento via boleto retornando descrição correta")
    void should_process_boleto_payment() {
        String result = processor.process(new PaymentProcessor.Boleto("34191790010104351004791020150008", 75.0));
        assertNotNull(result);
        assertTrue(result.toLowerCase().contains("boleto"), "Resultado deveria mencionar boleto: " + result);
        assertTrue(result.contains("75"), "Resultado deveria conter o valor: " + result);
    }

    @Test
    @DisplayName("deve calcular taxa diferente para cada tipo de pagamento")
    void should_calculate_different_fees_per_payment_type() {
        double creditFee = processor.calculateFee(new PaymentProcessor.CreditCard("1111", 100.0));
        double pixFee = processor.calculateFee(new PaymentProcessor.Pix("x@y.com", 100.0));
        double boletoFee = processor.calculateFee(new PaymentProcessor.Boleto("123", 100.0));

        assertTrue(creditFee > 0, "Cartão de crédito deveria ter taxa maior que zero");
        assertEquals(0.0, pixFee, 0.001, "PIX deveria ter taxa zero");
        assertTrue(boletoFee >= 0, "Boleto deveria ter taxa não-negativa");
        assertNotEquals(creditFee, boletoFee, "Taxas de cartão e boleto deveriam ser diferentes");
    }

    @Test
    @DisplayName("a interface Payment deve ser sealed e permitir apenas tipos específicos")
    void payment_should_be_sealed() {
        Class<?> paymentClass = PaymentProcessor.Payment.class;
        assertTrue(paymentClass.isSealed(), "Payment deveria ser sealed");
        Class<?>[] permitted = paymentClass.getPermittedSubclasses();
        assertNotNull(permitted);
        assertEquals(3, permitted.length, "Payment deveria permitir exatamente 3 subtipos");
    }
}
