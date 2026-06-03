package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("URLs RESTful para operações em Observation")
class FhirRestUrlsTest {

    private FhirRestUrls subject;

    @BeforeEach
    void setUp() {
        subject = new FhirRestUrls();
    }

    @Test
    @DisplayName("Criar Observation usa /Observation")
    void create_url() {
        assertEquals("/Observation", subject.urlForCreate());
    }

    @Test
    @DisplayName("Ler Observation 123 usa /Observation/123")
    void read_url() {
        assertEquals("/Observation/123", subject.urlForRead());
    }

    @Test
    @DisplayName("vread da versão 4 usa /Observation/123/_history/4")
    void vread_url() {
        assertEquals("/Observation/123/_history/4", subject.urlForVread());
    }

    @Test
    @DisplayName("Histórico da instância 123 usa /Observation/123/_history")
    void history_instance_url() {
        assertEquals("/Observation/123/_history", subject.urlForHistoryInstance());
    }

    @Test
    @DisplayName("Busca por paciente usa /Observation?patient=pat-7")
    void search_by_patient_url() {
        assertEquals("/Observation?patient=pat-7", subject.urlForSearchByPatient());
    }

    @Test
    @DisplayName("Histórico do tipo Observation usa /Observation/_history")
    void type_history_url() {
        assertEquals("/Observation/_history", subject.urlForTypeHistory());
    }
}
