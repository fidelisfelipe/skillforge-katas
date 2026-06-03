package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Interações condicionais e cabeçalhos HTTP no FHIR")
class FhirConditionalInteractionsTest {

    private FhirConditionalInteractions subject;

    @BeforeEach
    void setUp() {
        subject = new FhirConditionalInteractions();
    }

    @Test
    @DisplayName("Conditional create usa o cabeçalho If-None-Exist")
    void conditional_create_header() {
        assertEquals("If-None-Exist", subject.headerForConditionalCreate());
    }

    @Test
    @DisplayName("Conditional update usa o verbo PUT")
    void conditional_update_verb() {
        assertEquals("PUT", subject.verbForConditionalUpdate());
    }

    @Test
    @DisplayName("Conditional update de Patient por identifier MRN|12345")
    void conditional_update_url() {
        assertEquals("/Patient?identifier=MRN|12345", subject.urlForConditionalUpdate());
    }

    @Test
    @DisplayName("Conditional delete usa o verbo DELETE")
    void conditional_delete_verb() {
        assertEquals("DELETE", subject.verbForConditionalDelete());
    }

    @Test
    @DisplayName("Conditional read por ETag usa If-None-Match")
    void conditional_read_etag_header() {
        assertEquals("If-None-Match", subject.headerForConditionalReadByEtag());
    }

    @Test
    @DisplayName("Conditional read por data usa If-Modified-Since")
    void conditional_read_date_header() {
        assertEquals("If-Modified-Since", subject.headerForConditionalReadByDate());
    }
}
