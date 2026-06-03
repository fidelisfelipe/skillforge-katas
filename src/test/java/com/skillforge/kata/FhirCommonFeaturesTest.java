package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Aplicando a regra 80/20 e características comuns dos recursos")
class FhirCommonFeaturesTest {

    private FhirCommonFeatures subject;

    @BeforeEach
    void setUp() {
        subject = new FhirCommonFeatures();
    }

    @Test
    @DisplayName("deve indicar o percentual de casos de uso cobertos pela regra 80/20")
    void should_return_use_case_percentage() {
        assertEquals(80, subject.useCasePercentageCovered());
    }

    @Test
    @DisplayName("deve indicar o percentual de recursos da regra 80/20")
    void should_return_resource_percentage() {
        assertEquals(20, subject.resourcePercentageNeeded());
    }

    @Test
    @DisplayName("deve identificar 'metadata' como elemento de informações sobre o recurso")
    void should_identify_metadata_element() {
        assertEquals("metadata", subject.resourceInfoElement());
    }

    @Test
    @DisplayName("deve identificar 'narrative' como representação legível por humanos")
    void should_identify_narrative_element() {
        assertEquals("narrative", subject.humanReadableElement());
    }

    @Test
    @DisplayName("deve identificar 'extension' como mecanismo de extensibilidade")
    void should_identify_extension_mechanism() {
        assertEquals("extension", subject.extensibilityMechanism());
    }

    @Test
    @DisplayName("deve confirmar que recursos FHIR possuem URL identificadora")
    void should_confirm_unique_url_per_resource() {
        assertTrue(subject.resourcesHaveUniqueUrl());
    }
}
