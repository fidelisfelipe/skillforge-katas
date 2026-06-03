package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Classificando categorias de recursos FHIR")
class FhirResourceCategoriesTest {

    private FhirResourceCategories subject;

    @BeforeEach
    void setUp() {
        subject = new FhirResourceCategories();
    }

    @Test
    @DisplayName("deve identificar Foundation como categoria de infraestrutura")
    void should_identify_foundation_category() {
        assertEquals("Foundation", subject.infrastructureCategory());
    }

    @Test
    @DisplayName("deve identificar Clinical como categoria de conteúdo clínico")
    void should_identify_clinical_category() {
        assertEquals("Clinical", subject.clinicalCategory());
    }

    @Test
    @DisplayName("deve identificar Specialized como categoria de recursos especializados")
    void should_identify_specialized_category() {
        assertEquals("Specialized", subject.specializedCategory());
    }

    @Test
    @DisplayName("deve rejeitar 'Logical' como categoria oficial")
    void should_reject_logical_as_official_category() {
        assertFalse(subject.isLogicalAnOfficialCategory());
    }

    @Test
    @DisplayName("deve confirmar que Base é uma categoria existente")
    void should_confirm_base_category_exists() {
        assertTrue(subject.hasBaseCategory());
    }

    @Test
    @DisplayName("deve identificar 5 como o nível máximo de maturidade FMM")
    void should_return_max_fmm_level() {
        assertEquals(5, subject.maxMaturityLevel());
    }
}
