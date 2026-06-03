package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Extensões oficiais para dados demográficos do paciente")
class FhirPatientExtensionRegistryTest {

    private FhirPatientExtensionRegistry subject;

    @BeforeEach
    void setUp() {
        subject = new FhirPatientExtensionRegistry();
    }

    @Test
    @DisplayName("URL canônica da extensão patient-birthPlace")
    void birth_place_url() {
        assertEquals("http://hl7.org/fhir/StructureDefinition/patient-birthPlace", subject.birthPlaceExtensionUrl());
    }

    @Test
    @DisplayName("URL canônica da extensão patient-mothersMaidenName")
    void mothers_maiden_name_url() {
        assertEquals("http://hl7.org/fhir/StructureDefinition/patient-mothersMaidenName", subject.mothersMaidenNameExtensionUrl());
    }

    @Test
    @DisplayName("URL canônica da extensão us-core-race")
    void us_core_race_url() {
        assertEquals("http://hl7.org/fhir/us/core/StructureDefinition/us-core-race", subject.usCoreRaceExtensionUrl());
    }

    @Test
    @DisplayName("URL canônica da extensão us-core-ethnicity")
    void us_core_ethnicity_url() {
        assertEquals("http://hl7.org/fhir/us/core/StructureDefinition/us-core-ethnicity", subject.usCoreEthnicityExtensionUrl());
    }

    @Test
    @DisplayName("URL canônica da extensão patient-genderIdentity")
    void gender_identity_url() {
        assertEquals("http://hl7.org/fhir/StructureDefinition/patient-genderIdentity", subject.genderIdentityExtensionUrl());
    }

    @Test
    @DisplayName("Reconhece extensão oficial pelo prefixo da URL")
    void recognizes_official_extension() {
        assertTrue(subject.isOfficialExtension("http://hl7.org/fhir/StructureDefinition/patient-birthPlace"));
        assertTrue(subject.isOfficialExtension("http://hl7.org/fhir/us/core/StructureDefinition/us-core-race"));
        assertFalse(subject.isOfficialExtension("http://meu-hospital.com.br/fhir/Extension/custom"));
    }
}
