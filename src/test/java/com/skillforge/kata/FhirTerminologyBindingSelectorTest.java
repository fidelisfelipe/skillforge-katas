package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Identificando a terminologia correta para cada elemento clínico")
class FhirTerminologyBindingSelectorTest {

    private FhirTerminologyBindingSelector subject;

    @BeforeEach
    void setUp() {
        subject = new FhirTerminologyBindingSelector();
    }

    @Test
    @DisplayName("Observation.code de glicemia em jejum deve usar LOINC")
    void glucose_observation_uses_loinc() {
        assertEquals("LOINC", subject.terminologyForGlucoseObservation());
    }

    @Test
    @DisplayName("Condition.code de hipertensão deve usar SNOMED CT")
    void hypertension_condition_uses_snomed() {
        assertEquals("SNOMED CT", subject.terminologyForHypertensionCondition());
    }

    @Test
    @DisplayName("Quantity.code da pressão arterial em mmHg deve usar UCUM")
    void blood_pressure_unit_uses_ucum() {
        assertEquals("UCUM", subject.terminologyForBloodPressureUnit());
    }

    @Test
    @DisplayName("MedicationRequest de losartana deve usar RxNorm")
    void losartan_prescription_uses_rxnorm() {
        assertEquals("RxNorm", subject.terminologyForLosartanPrescription());
    }

    @Test
    @DisplayName("Observation.code de hemograma completo deve usar LOINC")
    void cbc_observation_uses_loinc() {
        assertEquals("LOINC", subject.terminologyForCompleteBloodCount());
    }
}
