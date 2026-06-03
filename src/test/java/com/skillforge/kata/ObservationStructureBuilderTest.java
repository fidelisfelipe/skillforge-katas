package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Observation completa: elementos obrigatórios e referências")
class ObservationStructureBuilderTest {

    private ObservationStructureBuilder subject;

    @BeforeEach
    void setUp() {
        subject = new ObservationStructureBuilder();
    }

    @Test
    @DisplayName("Observation.subject referencia o Patient do sinal vital")
    void subject_references_patient() {
        assertEquals("subject", subject.elementForPatientReference());
        assertEquals("Patient", subject.resourceTypeForSubject());
    }

    @Test
    @DisplayName("Observation.performer referencia o profissional que mediu")
    void performer_references_practitioner() {
        assertEquals("performer", subject.elementForPerformer());
        assertEquals("Practitioner", subject.resourceTypeForPerformer());
    }

    @Test
    @DisplayName("Observation.code carrega o código do que é observado")
    void code_carries_what_is_observed() {
        assertEquals("code", subject.elementForObservationCode());
    }

    @Test
    @DisplayName("sinais vitais usam LOINC como sistema de terminologia")
    void vital_signs_use_loinc() {
        assertEquals("LOINC", subject.terminologyForVitalSignCode());
    }

    @Test
    @DisplayName("Quantity.system de sinais vitais deve ser UCUM")
    void quantity_system_is_ucum() {
        assertEquals("UCUM", subject.unitSystemForQuantity());
    }

    @Test
    @DisplayName("interpretation indica se o valor está alto, baixo ou normal")
    void interpretation_indicates_value_classification() {
        assertEquals("interpretation", subject.elementForResultInterpretation());
    }
}
