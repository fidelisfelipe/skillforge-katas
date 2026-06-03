package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Must Support e regras de elementos em perfis FHIR")
class FhirMustSupportRulesTest {

    private FhirMustSupportRules subject;

    @BeforeEach
    void setUp() {
        subject = new FhirMustSupportRules();
    }

    @Test
    @DisplayName("Servidor SHALL popular elemento Must Support quando há dado disponível")
    void server_must_populate_when_available() {
        assertEquals("populate", subject.serverObligationWhenDataAvailable());
    }

    @Test
    @DisplayName("Cliente SHALL processar elemento Must Support recebido")
    void client_must_process_received() {
        assertEquals("process", subject.clientObligationWhenReceived());
    }

    @Test
    @DisplayName("Must Support não implica cardinalidade mínima 1")
    void must_support_does_not_imply_mandatory() {
        assertFalse(subject.mustSupportImpliesMandatory());
    }

    @Test
    @DisplayName("Símbolo de elemento summary é sigma (Σ)")
    void summary_flag_is_sigma() {
        assertEquals("Σ", subject.summaryFlagSymbol());
    }

    @Test
    @DisplayName("Abreviação de elemento modifier é ?!")
    void modifier_flag_abbreviation() {
        assertEquals("?!", subject.modifierFlagAbbreviation());
    }

    @Test
    @DisplayName("Receptor NÃO pode ignorar modifierExtension desconhecida")
    void cannot_ignore_unknown_modifier_extension() {
        assertFalse(subject.canIgnoreUnknownModifierExtension());
    }
}
