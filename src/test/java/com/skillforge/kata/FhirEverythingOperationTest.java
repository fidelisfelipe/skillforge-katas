package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Operação $everything para exportar dados do paciente")
class FhirEverythingOperationTest {

    private FhirEverythingOperation subject;

    @BeforeEach
    void setUp() {
        subject = new FhirEverythingOperation();
    }

    @Test
    @DisplayName("O nome da operação é $everything (com prefixo)")
    void operation_name() {
        assertEquals("$everything", subject.operationName());
    }

    @Test
    @DisplayName("$everything é uma operação de nível instance")
    void operation_level() {
        assertEquals("instance", subject.operationLevel());
    }

    @Test
    @DisplayName("URL para tudo do paciente 42 é Patient/42/$everything")
    void url_for_patient_everything() {
        assertEquals("Patient/42/$everything", subject.urlForPatientEverything());
    }

    @Test
    @DisplayName("$everything retorna um Bundle do tipo searchset")
    void response_bundle_type() {
        assertEquals("searchset", subject.responseBundleType());
    }

    @Test
    @DisplayName("$everything também está disponível no recurso Encounter")
    void available_on_encounter() {
        assertTrue(subject.isAlsoAvailableOnEncounter());
    }

    @Test
    @DisplayName("Parâmetro 'start' delimita o início do intervalo de datas")
    void parameter_name_for_start() {
        assertEquals("start", subject.parameterNameForStart());
    }

    @Test
    @DisplayName("Parâmetro 'end' delimita o fim do intervalo de datas")
    void parameter_name_for_end() {
        assertEquals("end", subject.parameterNameForEnd());
    }
}
