package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Patient: óbito, identificadores e segurança")
class PatientElementSelectorTest {

    private PatientElementSelector subject;

    @BeforeEach
    void setUp() {
        subject = new PatientElementSelector();
    }

    @Test
    @DisplayName("deve usar deceasedBoolean quando apenas o fato do óbito é conhecido")
    void deceased_without_date_uses_boolean_variant() {
        assertEquals("deceasedBoolean", subject.elementForDeceasedWithoutDate());
    }

    @Test
    @DisplayName("deve usar deceasedDateTime quando a data do óbito é conhecida")
    void deceased_with_date_uses_datetime_variant() {
        assertEquals("deceasedDateTime", subject.elementForDeceasedWithKnownDate());
    }

    @Test
    @DisplayName("Patient.identifier deve ter cardinalidade mínima 0 e permitir repetição")
    void identifier_cardinality_is_zero_to_many() {
        assertEquals(0, subject.minIdentifierCardinality());
        assertTrue(subject.canIdentifierRepeat());
    }

    @Test
    @DisplayName("sinalização de confidencialidade deve usar Patient.meta.security")
    void confidentiality_uses_meta_security() {
        assertEquals("Patient.meta.security", subject.elementForConfidentialityFlag());
    }

    @Test
    @DisplayName("idioma do conteúdo do recurso deve ser Patient.language e não Patient.communication.language")
    void resource_content_language_uses_patient_language() {
        assertEquals("language", subject.elementForResourceLanguage());
    }
}
