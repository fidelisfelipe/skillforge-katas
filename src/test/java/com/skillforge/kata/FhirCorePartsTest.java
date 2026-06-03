package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Compreendendo as duas partes principais do FHIR")
class FhirCorePartsTest {

    private FhirCoreParts subject;

    @BeforeEach
    void setUp() {
        subject = new FhirCoreParts();
    }

    @Test
    @DisplayName("deve identificar Content Model como primeira parte do FHIR")
    void should_identify_content_model_part() {
        assertEquals("Content Model", subject.firstMainPart());
    }

    @Test
    @DisplayName("deve identificar APIs como segunda parte do FHIR")
    void should_identify_apis_part() {
        assertEquals("APIs", subject.secondMainPart());
    }

    @Test
    @DisplayName("deve identificar REST como estilo arquitetural principal")
    void should_identify_rest_style() {
        assertEquals("REST", subject.mainArchitecturalStyle());
    }

    @Test
    @DisplayName("deve confirmar suporte ao paradigma de Messaging")
    void should_support_messaging() {
        assertTrue(subject.supportsMessaging());
    }

    @Test
    @DisplayName("deve confirmar suporte ao paradigma de Documents")
    void should_support_documents() {
        assertTrue(subject.supportsDocuments());
    }

    @Test
    @DisplayName("deve confirmar suporte ao paradigma de Services")
    void should_support_services() {
        assertTrue(subject.supportsServices());
    }

    @Test
    @DisplayName("deve totalizar 4 paradigmas de troca suportados pelo FHIR")
    void should_count_exchange_paradigms() {
        assertEquals(4, subject.totalExchangeParadigms());
    }
}
