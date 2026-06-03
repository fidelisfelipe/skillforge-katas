package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Operações de terminologia: $expand e $lookup")
class FhirTerminologyOperationsTest {

    private FhirTerminologyOperations subject;

    @BeforeEach
    void setUp() {
        subject = new FhirTerminologyOperations();
    }

    @Test
    @DisplayName("$expand é definida no recurso ValueSet")
    void resource_for_expand() {
        assertEquals("ValueSet", subject.resourceForExpandOperation());
    }

    @Test
    @DisplayName("$lookup é definida no recurso CodeSystem")
    void resource_for_lookup() {
        assertEquals("CodeSystem", subject.resourceForLookupOperation());
    }

    @Test
    @DisplayName("$expand retorna um ValueSet expandido")
    void expand_returns_valueset() {
        assertEquals("ValueSet", subject.expandReturnsResourceType());
    }

    @Test
    @DisplayName("$lookup retorna um recurso Parameters")
    void lookup_returns_parameters() {
        assertEquals("Parameters", subject.lookupReturnsResourceType());
    }

    @Test
    @DisplayName("Parâmetro 'filter' do $expand permite busca textual nos códigos")
    void parameter_name_for_filter() {
        assertEquals("filter", subject.parameterNameForFilter());
    }

    @Test
    @DisplayName("Parâmetro 'code' do $lookup identifica o código a consultar")
    void parameter_name_for_code() {
        assertEquals("code", subject.parameterNameForCode());
    }

    @Test
    @DisplayName("Parâmetro 'system' do $lookup identifica a URL do CodeSystem")
    void parameter_name_for_system() {
        assertEquals("system", subject.parameterNameForSystem());
    }
}
