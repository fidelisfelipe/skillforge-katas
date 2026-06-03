package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Bundle de batch e negociação de conteúdo no FHIR")
class FhirBatchAndFormatTest {

    private FhirBatchAndFormat subject;

    @BeforeEach
    void setUp() {
        subject = new FhirBatchAndFormat();
    }

    @Test
    @DisplayName("Bundle batch é enviado via POST")
    void batch_verb() {
        assertEquals("POST", subject.verbForBatch());
    }

    @Test
    @DisplayName("Bundle batch é enviado para a raiz do servidor FHIR (/)")
    void batch_url() {
        assertEquals("/", subject.urlForBatch());
    }

    @Test
    @DisplayName("Bundle.type para operações independentes é 'batch'")
    void bundle_type_independent() {
        assertEquals("batch", subject.bundleTypeForIndependentOps());
    }

    @Test
    @DisplayName("Bundle.type para operações atômicas é 'transaction'")
    void bundle_type_atomic() {
        assertEquals("transaction", subject.bundleTypeForAtomic());
    }

    @Test
    @DisplayName("MIME type FHIR para JSON é application/fhir+json")
    void mime_json() {
        assertEquals("application/fhir+json", subject.mimeTypeForJson());
    }

    @Test
    @DisplayName("MIME type FHIR para XML é application/fhir+xml")
    void mime_xml() {
        assertEquals("application/fhir+xml", subject.mimeTypeForXml());
    }

    @Test
    @DisplayName("Cabeçalho para pedir resposta em JSON é Accept")
    void accept_header() {
        assertEquals("Accept", subject.headerToRequestJsonResponse());
    }
}
