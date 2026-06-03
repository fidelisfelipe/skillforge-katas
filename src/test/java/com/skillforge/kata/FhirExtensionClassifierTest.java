package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Extensões padrão versus extensões modificadoras")
class FhirExtensionClassifierTest {

    private FhirExtensionClassifier subject;

    @BeforeEach
    void setUp() {
        subject = new FhirExtensionClassifier();
    }

    @Test
    @DisplayName("qualifier-negation é uma modifierExtension")
    void negation_is_modifier_extension() {
        assertTrue(subject.isModifierExtension("qualifier-negation"));
    }

    @Test
    @DisplayName("patient-birthPlace não é uma modifierExtension")
    void birth_place_is_not_modifier_extension() {
        assertFalse(subject.isModifierExtension("patient-birthPlace"));
        assertFalse(subject.isModifierExtension("us-core-race"));
    }

    @Test
    @DisplayName("Receptor SHALL rejeitar modifierExtension desconhecida")
    void must_reject_unknown_modifier_extension() {
        assertTrue(subject.mustRejectIfUnknown("medication-not-given"));
        assertFalse(subject.mustRejectIfUnknown("patient-mothersMaidenName"));
    }

    @Test
    @DisplayName("Prefixo oficial de extensões HL7 é correto")
    void official_extension_url_prefix() {
        assertEquals("http://hl7.org/fhir/StructureDefinition/", subject.extensionUrlPrefix());
    }

    @Test
    @DisplayName("Não criar extensão custom se já existir oficial")
    void avoid_custom_when_official_exists() {
        assertFalse(subject.shouldCreateCustomExtension(true));
        assertTrue(subject.shouldCreateCustomExtension(false));
    }
}
