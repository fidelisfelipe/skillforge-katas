package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tipos de Bundle em FHIR")
class FhirBundleTypesTest {

    private FhirBundleTypes subject;

    @BeforeEach
    void setUp() {
        subject = new FhirBundleTypes();
    }

    @Test
    @DisplayName("Resultado de busca FHIR retorna Bundle do tipo 'searchset'")
    void should_return_searchset_for_search_results() {
        assertEquals("searchset", subject.typeForSearchResults());
    }

    @Test
    @DisplayName("Operações atômicas (all-or-nothing) usam Bundle do tipo 'transaction'")
    void should_return_transaction_for_atomic_operations() {
        assertEquals("transaction", subject.typeForAtomicOperations());
    }

    @Test
    @DisplayName("Operações independentes com sucesso parcial usam Bundle do tipo 'batch'")
    void should_return_batch_for_independent_operations() {
        assertEquals("batch", subject.typeForIndependentOperations());
    }

    @Test
    @DisplayName("Prontuário clínico assinado é um Bundle do tipo 'document'")
    void should_return_document_for_clinical_document() {
        assertEquals("document", subject.typeForClinicalDocument());
    }

    @Test
    @DisplayName("Mensagem com MessageHeader é um Bundle do tipo 'message'")
    void should_return_message_for_messaging() {
        assertEquals("message", subject.typeForMessaging());
    }

    @Test
    @DisplayName("Resposta de _history é um Bundle do tipo 'history'")
    void should_return_history_for_version_history() {
        assertEquals("history", subject.typeForResourceHistory());
    }
}
