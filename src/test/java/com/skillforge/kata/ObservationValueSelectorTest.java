package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Observation: escolhendo o value[x] correto")
class ObservationValueSelectorTest {

    private ObservationValueSelector subject;

    @BeforeEach
    void setUp() {
        subject = new ObservationValueSelector();
    }

    @Test
    @DisplayName("pressão arterial numérica com unidade UCUM deve usar valueQuantity")
    void systolic_pressure_uses_value_quantity() {
        assertEquals("valueQuantity", subject.valueForBloodPressureSystolic());
    }

    @Test
    @DisplayName("laudo em PDF deve usar valueAttachment")
    void scanned_pdf_uses_value_attachment() {
        assertEquals("valueAttachment", subject.valueForScannedReportPdf());
    }

    @Test
    @DisplayName("tipagem sanguínea como texto livre deve usar valueString")
    void blood_type_uses_value_string() {
        assertEquals("valueString", subject.valueForBloodTypeABPositive());
    }

    @Test
    @DisplayName("forma de onda contínua de ECG deve usar valueSampledData")
    void ecg_waveform_uses_value_sampled_data() {
        assertEquals("valueSampledData", subject.valueForEcgWaveform());
    }

    @Test
    @DisplayName("status é o elemento obrigatório de Observation")
    void status_is_the_mandatory_element() {
        assertEquals("status", subject.mandatoryStatusElement());
    }

    @Test
    @DisplayName("momento único de coleta deve usar effectiveDateTime")
    void single_instant_collection_uses_effective_datetime() {
        assertEquals("effectiveDateTime", subject.elementForObservationDateTime());
    }
}
