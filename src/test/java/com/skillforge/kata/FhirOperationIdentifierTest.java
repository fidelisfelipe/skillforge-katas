package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Identificando operações FHIR pelo prefixo $")
class FhirOperationIdentifierTest {

    private FhirOperationIdentifier subject;

    @BeforeEach
    void setUp() {
        subject = new FhirOperationIdentifier();
    }

    @Test
    @DisplayName("Operação para validar recurso contra profile é $validate")
    void validate_operation_name() {
        assertEquals("$validate", subject.operationToValidateResource());
    }

    @Test
    @DisplayName("Operação para expandir ValueSet é $expand")
    void expand_operation_name() {
        assertEquals("$expand", subject.operationToExpandValueSet());
    }

    @Test
    @DisplayName("Operação para consultar código em CodeSystem é $lookup")
    void lookup_operation_name() {
        assertEquals("$lookup", subject.operationToLookupCode());
    }

    @Test
    @DisplayName("Operação para obter todos os recursos do paciente é $everything")
    void everything_operation_name() {
        assertEquals("$everything", subject.operationToGetAllPatientData());
    }

    @Test
    @DisplayName("Operação para traduzir códigos entre sistemas é $translate")
    void translate_operation_name() {
        assertEquals("$translate", subject.operationToTranslateCode());
    }

    @Test
    @DisplayName("O prefixo de operações FHIR é o caractere $")
    void operation_prefix_character() {
        assertEquals("$", subject.operationPrefixCharacter());
    }
}
