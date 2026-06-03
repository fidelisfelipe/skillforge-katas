package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Escolhendo o nível correto da operação $validate")
class FhirValidateOperationTest {

    private FhirValidateOperation subject;

    @BeforeEach
    void setUp() {
        subject = new FhirValidateOperation();
    }

    @Test
    @DisplayName("$validate é invocado com verbo HTTP POST")
    void verb_for_validate() {
        assertEquals("POST", subject.verbForValidate());
    }

    @Test
    @DisplayName("URL para validar recurso Patient em nível type é Patient/$validate")
    void type_level_validate_url() {
        assertEquals("Patient/$validate", subject.typeLevelValidateUrl());
    }

    @Test
    @DisplayName("URL para validar instância específica é Patient/123/$validate")
    void instance_level_validate_url() {
        assertEquals("Patient/123/$validate", subject.instanceLevelValidateUrl());
    }

    @Test
    @DisplayName("URL para validar em nível system é apenas $validate")
    void system_level_validate_url() {
        assertEquals("$validate", subject.systemLevelValidateUrl());
    }

    @Test
    @DisplayName("$validate retorna um OperationOutcome com as issues encontradas")
    void response_resource_type() {
        assertEquals("OperationOutcome", subject.responseResourceType());
    }

    @Test
    @DisplayName("Parâmetro 'profile' indica contra qual StructureDefinition validar")
    void parameter_name_for_profile() {
        assertEquals("profile", subject.parameterNameForProfile());
    }
}
