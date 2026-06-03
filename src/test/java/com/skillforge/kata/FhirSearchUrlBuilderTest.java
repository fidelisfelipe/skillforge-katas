package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Construção de URLs de busca FHIR")
class FhirSearchUrlBuilderTest {

    private FhirSearchUrlBuilder subject;

    @BeforeEach
    void setUp() {
        subject = new FhirSearchUrlBuilder();
    }

    @Test
    @DisplayName("URL para buscar Patient por sobrenome usa name=Silva")
    void should_build_patient_search_by_name() {
        assertEquals("https://hapi.example.org/fhir/Patient?name=Silva", subject.searchPatientByName());
    }

    @Test
    @DisplayName("URL para buscar Observations do paciente 123 usa subject=Patient/123")
    void should_build_observation_search_by_subject() {
        assertEquals("https://hapi.example.org/fhir/Observation?subject=Patient/123", subject.searchObservationsForPatient());
    }

    @Test
    @DisplayName("URL para buscar Conditions ativas usa clinical-status=active")
    void should_build_active_conditions_search() {
        assertEquals("https://hapi.example.org/fhir/Condition?clinical-status=active", subject.searchActiveConditions());
    }

    @Test
    @DisplayName("URL para limitar a 20 Encounters por página usa _count=20")
    void should_build_encounter_search_with_count() {
        assertEquals("https://hapi.example.org/fhir/Encounter?_count=20", subject.searchEncountersLimited());
    }

    @Test
    @DisplayName("URL para incluir o Patient referenciado na busca de Observation usa _include=Observation:subject")
    void should_build_observation_search_with_include() {
        assertEquals("https://hapi.example.org/fhir/Observation?_include=Observation:subject", subject.searchObservationsIncludingPatient());
    }
}
