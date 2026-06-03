package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Distinguindo CodeSystem, ValueSet e Binding")
class FhirTerminologyArtifactSelectorTest {

    private FhirTerminologyArtifactSelector subject;

    @BeforeEach
    void setUp() {
        subject = new FhirTerminologyArtifactSelector();
    }

    @Test
    @DisplayName("Catálogo completo de SNOMED CT é um CodeSystem")
    void snomed_catalog_is_code_system() {
        assertEquals("CodeSystem", subject.artifactForSnomedCatalog());
    }

    @Test
    @DisplayName("Subconjunto de LOINC para sinais vitais é um ValueSet")
    void vital_signs_subset_is_value_set() {
        assertEquals("ValueSet", subject.artifactForVitalSignsSubset());
    }

    @Test
    @DisplayName("Vínculo entre Observation.code e códigos permitidos é um Binding")
    void link_element_to_codes_is_binding() {
        assertEquals("Binding", subject.artifactForLinkingElementToCodes());
    }

    @Test
    @DisplayName("Dicionário interno de tipos de leito é um CodeSystem")
    void internal_bed_types_is_code_system() {
        assertEquals("CodeSystem", subject.artifactForInternalBedTypes());
    }

    @Test
    @DisplayName("Lista filtrada de antidiabéticos do RxNorm é um ValueSet")
    void antidiabetic_list_is_value_set() {
        assertEquals("ValueSet", subject.artifactForAntidiabeticList());
    }

    @Test
    @DisplayName("Observation.status usa binding required")
    void observation_status_binding_is_required() {
        assertEquals("required", subject.bindingStrengthForObservationStatus());
    }
}
