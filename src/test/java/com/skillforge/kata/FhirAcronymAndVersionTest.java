package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Identificando o significado e versão atual do FHIR")
class FhirAcronymAndVersionTest {

    private FhirAcronymAndVersion subject;

    @BeforeEach
    void setUp() {
        subject = new FhirAcronymAndVersion();
    }

    @Test
    @DisplayName("deve expandir corretamente a sigla FHIR")
    void should_expand_fhir_acronym() {
        assertEquals("Fast Healthcare Interoperability Resources", subject.fhirAcronymMeaning());
    }

    @Test
    @DisplayName("deve identificar a organização mantenedora do padrão FHIR")
    void should_identify_maintainer_organization() {
        assertEquals("HL7", subject.maintainerOrganization());
    }

    @Test
    @DisplayName("deve informar a versão atual do FHIR")
    void should_return_current_fhir_version() {
        assertEquals("R5", subject.currentVersion());
    }

    @Test
    @DisplayName("deve informar a versão anterior à atual")
    void should_return_previous_fhir_version() {
        assertEquals("R4", subject.previousVersion());
    }

    @Test
    @DisplayName("deve classificar o FHIR como padrão aberto")
    void should_identify_fhir_as_open_standard() {
        assertEquals("open", subject.standardType());
    }
}
