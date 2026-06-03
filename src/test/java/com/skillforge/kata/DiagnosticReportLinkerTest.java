package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DiagnosticReport e referências entre recursos")
class DiagnosticReportLinkerTest {

    private DiagnosticReportLinker subject;

    @BeforeEach
    void setUp() {
        subject = new DiagnosticReportLinker();
    }

    @Test
    @DisplayName("DiagnosticReport.basedOn referencia a requisição original")
    void based_on_links_to_original_order() {
        assertEquals("basedOn", subject.elementLinkingToOriginalOrder());
    }

    @Test
    @DisplayName("a requisição original é um ServiceRequest")
    void original_order_is_service_request() {
        assertEquals("ServiceRequest", subject.resourceTypeForOriginalOrder());
    }

    @Test
    @DisplayName("quando o id FHIR do alvo é conhecido, usa-se referência literal")
    void known_id_uses_literal_reference() {
        assertEquals("LITERAL", subject.referenceStrategyWhenIdKnown());
    }

    @Test
    @DisplayName("quando apenas o identificador de negócio é conhecido, usa-se referência lógica")
    void business_id_uses_logical_reference() {
        assertEquals("LOGICAL", subject.referenceStrategyWhenOnlyBusinessIdKnown());
        assertEquals("Reference.identifier", subject.elementForLogicalReference());
    }

    @Test
    @DisplayName("o conjunto de imagens DICOM é representado por ImagingStudy")
    void radiology_image_set_is_imaging_study() {
        assertEquals("ImagingStudy", subject.resourceForRadiologyImageSet());
    }
}
