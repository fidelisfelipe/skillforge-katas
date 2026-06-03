package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Parâmetros de busca comuns em FHIR")
class FhirSearchParametersTest {

    private FhirSearchParameters subject;

    @BeforeEach
    void setUp() {
        subject = new FhirSearchParameters();
    }

    @Test
    @DisplayName("Patient deve ser buscado por data de nascimento usando o parâmetro 'birthdate'")
    void should_return_birthdate_parameter_for_patient() {
        assertEquals("birthdate", subject.parameterForPatientBirthDate());
    }

    @Test
    @DisplayName("Observation deve referenciar o paciente pelo parâmetro 'subject'")
    void should_return_subject_parameter_for_observation_patient() {
        assertEquals("subject", subject.parameterForObservationPatient());
    }

    @Test
    @DisplayName("Busca por id lógico do recurso usa o parâmetro comum '_id'")
    void should_return_underscore_id_for_logical_id_search() {
        assertEquals("_id", subject.parameterForLogicalId());
    }

    @Test
    @DisplayName("Busca por data de modificação usa o parâmetro comum '_lastUpdated'")
    void should_return_last_updated_parameter() {
        assertEquals("_lastUpdated", subject.parameterForLastModified());
    }

    @Test
    @DisplayName("Observation deve ser buscada pelo código LOINC usando o parâmetro 'code'")
    void should_return_code_parameter_for_observation_loinc() {
        assertEquals("code", subject.parameterForObservationCode());
    }

    @Test
    @DisplayName("Patient deve ser buscado por identificador de negócio usando 'identifier'")
    void should_return_identifier_parameter_for_patient_business_id() {
        assertEquals("identifier", subject.parameterForPatientBusinessIdentifier());
    }
}
