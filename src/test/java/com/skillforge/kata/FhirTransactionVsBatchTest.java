package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Transaction vs Batch Bundle")
class FhirTransactionVsBatchTest {

    private FhirTransactionVsBatch subject;

    @BeforeEach
    void setUp() {
        subject = new FhirTransactionVsBatch();
    }

    @Test
    @DisplayName("Bundle atômico é do tipo 'transaction'")
    void should_identify_atomic_bundle_type() {
        assertEquals("transaction", subject.atomicBundleType());
    }

    @Test
    @DisplayName("Bundle que permite sucesso parcial é do tipo 'batch'")
    void should_identify_partial_success_bundle_type() {
        assertEquals("batch", subject.partialSuccessBundleType());
    }

    @Test
    @DisplayName("Em um transaction, falha em uma entrada reverte todas as demais")
    void should_rollback_transaction_on_failure() {
        assertTrue(subject.transactionRollsBackOnFailure());
    }

    @Test
    @DisplayName("Em um batch, as entradas são processadas independentemente")
    void should_process_batch_entries_independently() {
        assertTrue(subject.batchEntriesAreIndependent());
    }

    @Test
    @DisplayName("Resposta do servidor a um transaction é do tipo 'transaction-response'")
    void should_return_transaction_response_type() {
        assertEquals("transaction-response", subject.transactionResponseType());
    }

    @Test
    @DisplayName("Resposta do servidor a um batch é do tipo 'batch-response'")
    void should_return_batch_response_type() {
        assertEquals("batch-response", subject.batchResponseType());
    }

    @Test
    @DisplayName("Bundle transaction suporta referências internas via urn:uuid entre entradas")
    void should_support_urn_uuid_references_in_transaction() {
        assertTrue(subject.transactionSupportsInternalReferences());
    }
}
