package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Histórico, versionamento e update-as-create no FHIR")
class FhirHistoryAndVersioningTest {

    private FhirHistoryAndVersioning subject;

    @BeforeEach
    void setUp() {
        subject = new FhirHistoryAndVersioning();
    }

    @Test
    @DisplayName("Histórico da instância rx-99 usa /MedicationRequest/rx-99/_history")
    void instance_history_url() {
        assertEquals("/MedicationRequest/rx-99/_history", subject.urlForInstanceHistory());
    }

    @Test
    @DisplayName("vread da versão 3 usa /MedicationRequest/rx-99/_history/3")
    void vread_url() {
        assertEquals("/MedicationRequest/rx-99/_history/3", subject.urlForVread());
    }

    @Test
    @DisplayName("Update-as-create usa o verbo PUT")
    void update_as_create_verb() {
        assertEquals("PUT", subject.verbForUpdateAsCreate());
    }

    @Test
    @DisplayName("Update-as-create de rx-99 usa /MedicationRequest/rx-99")
    void update_as_create_url() {
        assertEquals("/MedicationRequest/rx-99", subject.urlForUpdateAsCreate());
    }

    @Test
    @DisplayName("Servidor expõe versão atual no cabeçalho ETag")
    void etag_header() {
        assertEquals("ETag", subject.headerExposingCurrentVersion());
    }

    @Test
    @DisplayName("O versionId fica em meta.versionId")
    void version_id_element() {
        assertEquals("versionId", subject.elementHoldingVersionId());
    }

    @Test
    @DisplayName("Histórico do tipo MedicationRequest usa /MedicationRequest/_history")
    void type_history_url() {
        assertEquals("/MedicationRequest/_history", subject.urlForTypeHistory());
    }
}
