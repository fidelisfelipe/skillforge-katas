package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Busca avançada: _include, _summary e chained search")
class FhirAdvancedSearchTest {

    private FhirAdvancedSearch subject;

    @BeforeEach
    void setUp() {
        subject = new FhirAdvancedSearch();
    }

    @Test
    @DisplayName("Parâmetro para incluir recursos referenciados é '_include'")
    void should_return_include_parameter() {
        assertEquals("_include", subject.parameterToIncludeReferencedResources());
    }

    @Test
    @DisplayName("_summary=true retorna apenas elementos marcados como Summary")
    void should_return_true_for_summary_elements_only() {
        assertEquals("true", subject.summaryValueForSummaryElementsOnly());
    }

    @Test
    @DisplayName("Chained search para Condition por nome do paciente usa subject:Patient.name")
    void should_build_chained_search_url() {
        assertEquals("https://hapi.example.org/fhir/Condition?subject:Patient.name=Smith", subject.searchConditionsByPatientName());
    }

    @Test
    @DisplayName("Busca de Observations do paciente 42 incluindo Patient usa _include=Observation:subject")
    void should_build_observation_search_with_include() {
        assertEquals("https://hapi.example.org/fhir/Observation?subject=Patient/42&_include=Observation:subject", subject.searchObservationsForPatientIncludingPatient());
    }

    @Test
    @DisplayName("Para retornar apenas a contagem total usa _summary=count")
    void should_build_count_only_search() {
        assertEquals("https://hapi.example.org/fhir/MedicationRequest?_summary=count", subject.searchMedicationRequestCountOnly());
    }

    @Test
    @DisplayName("Parâmetro para limitar recursos por página é '_count'")
    void should_return_count_parameter() {
        assertEquals("_count", subject.parameterToLimitResultsPerPage());
    }

    @Test
    @DisplayName("Busca por identifier no formato system|value")
    void should_build_identifier_search_with_system() {
        assertEquals("https://hapi.example.org/fhir/Patient?identifier=http://hospital.org/mrn|12345", subject.searchPatientByIdentifierWithSystem());
    }
}
