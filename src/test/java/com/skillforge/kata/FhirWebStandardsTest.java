package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Reconhecendo os padrões web utilizados pelo FHIR")
class FhirWebStandardsTest {

    private FhirWebStandards subject;

    @BeforeEach
    void setUp() {
        subject = new FhirWebStandards();
    }

    @Test
    @DisplayName("deve identificar JSON como formato textual suportado")
    void should_identify_json_format() {
        assertEquals("JSON", subject.primaryTextFormat());
    }

    @Test
    @DisplayName("deve identificar XML como formato de marcação alternativo")
    void should_identify_xml_format() {
        assertEquals("XML", subject.markupFormat());
    }

    @Test
    @DisplayName("deve identificar HTTP como protocolo de transporte")
    void should_identify_http_protocol() {
        assertEquals("HTTP", subject.transportProtocol());
    }

    @Test
    @DisplayName("deve identificar OAuth2 como padrão de autorização")
    void should_identify_oauth2_authorization() {
        assertEquals("OAUTH2", subject.authorizationStandard());
    }

    @Test
    @DisplayName("não deve listar CSV como formato nativo do FHIR")
    void should_not_support_csv_natively() {
        assertFalse(subject.supportsCsvNatively());
    }

    @Test
    @DisplayName("deve confirmar que FHIR foi projetado para implementadores")
    void should_be_implementer_focused() {
        assertTrue(subject.isImplementerFocused());
    }
}
