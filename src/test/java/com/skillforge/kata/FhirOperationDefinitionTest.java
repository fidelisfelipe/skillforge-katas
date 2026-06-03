package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Definindo e invocando operações com Parameters")
class FhirOperationDefinitionTest {

    private FhirOperationDefinition subject;

    @BeforeEach
    void setUp() {
        subject = new FhirOperationDefinition();
    }

    @Test
    @DisplayName("O recurso que define operações é OperationDefinition")
    void resource_that_defines_operations() {
        assertEquals("OperationDefinition", subject.resourceThatDefinesOperations());
    }

    @Test
    @DisplayName("O corpo padrão de entrada de uma operação é um Parameters")
    void default_input_resource_type() {
        assertEquals("Parameters", subject.defaultInputResourceType());
    }

    @Test
    @DisplayName("O retorno padrão de uma operação é um Parameters")
    void default_output_resource_type() {
        assertEquals("Parameters", subject.defaultOutputResourceType());
    }

    @Test
    @DisplayName("Operações com corpo Parameters usam verbo POST")
    void verb_for_operation_with_body() {
        assertEquals("POST", subject.verbForOperationWithBody());
    }

    @Test
    @DisplayName("Operações com parâmetros primitivos podem usar GET")
    void verb_for_operation_without_body() {
        assertEquals("GET", subject.verbForOperationWithoutBody());
    }

    @Test
    @DisplayName("O nome da operação sem $ fica no elemento 'code' do OperationDefinition")
    void operation_code_element() {
        assertEquals("code", subject.operationCodeElement());
    }

    @Test
    @DisplayName("A propriedade 'affectsState' indica se a operação altera estado")
    void affects_state_property() {
        assertEquals("affectsState", subject.isAffectStateProperty());
    }
}
