package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Verbos HTTP para interações CRUD básicas em recursos Patient")
class FhirCrudVerbsTest {

    private FhirCrudVerbs subject;

    @BeforeEach
    void setUp() {
        subject = new FhirCrudVerbs();
    }

    @Test
    @DisplayName("Criar um Patient novo no servidor usa POST")
    void create_uses_post() {
        assertEquals("POST", subject.verbForCreate());
    }

    @Test
    @DisplayName("Ler um Patient pelo id usa GET")
    void read_uses_get() {
        assertEquals("GET", subject.verbForRead());
    }

    @Test
    @DisplayName("Substituir integralmente um Patient usa PUT")
    void update_uses_put() {
        assertEquals("PUT", subject.verbForUpdate());
    }

    @Test
    @DisplayName("Atualizar parcialmente um Patient usa PATCH")
    void patch_uses_patch() {
        assertEquals("PATCH", subject.verbForPatch());
    }

    @Test
    @DisplayName("Remover um Patient usa DELETE")
    void delete_uses_delete() {
        assertEquals("DELETE", subject.verbForDelete());
    }

    @Test
    @DisplayName("Buscar Patients por parâmetros usa GET")
    void search_uses_get() {
        assertEquals("GET", subject.verbForSearch());
    }
}
