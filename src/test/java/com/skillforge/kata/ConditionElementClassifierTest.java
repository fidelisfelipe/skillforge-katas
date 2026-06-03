package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Condition: status, verificação e elementos modificadores")
class ConditionElementClassifierTest {

    private ConditionElementClassifier subject;

    @BeforeEach
    void setUp() {
        subject = new ConditionElementClassifier();
    }

    @Test
    @DisplayName("verificationStatus indica confirmação, refutação ou erro de lançamento")
    void verification_status_is_the_confirmation_element() {
        assertEquals("verificationStatus", subject.elementForConfirmationStatus());
    }

    @Test
    @DisplayName("verificationStatus é um modifierElement")
    void verification_status_is_a_modifier_element() {
        assertTrue(subject.isVerificationStatusModifier());
    }

    @Test
    @DisplayName("modifierExtension desconhecida obriga descartar o recurso inteiro")
    void unknown_modifier_extension_discards_resource() {
        assertEquals("DISCARD_RESOURCE", subject.handlingUnknownModifierExtension());
    }

    @Test
    @DisplayName("Condition.code deve usar SNOMED CT como terminologia preferencial")
    void condition_code_uses_snomed_ct() {
        assertEquals("SNOMED CT", subject.terminologyForConditionCode());
    }

    @Test
    @DisplayName("clinicalStatus indica ativa/remissão/resolvida e também é modificador")
    void clinical_status_is_modifier_element() {
        assertEquals("clinicalStatus", subject.elementForClinicalState());
        assertTrue(subject.isClinicalStatusModifier());
    }
}
