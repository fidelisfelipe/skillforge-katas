package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("UserProfileService: eliminação de null checks em cadeia com Optional")
class UserProfileServiceTest {

    private UserProfileService service;

    @BeforeEach
    void setUp() {
        service = new UserProfileService();
    }

    @Test
    @DisplayName("deve retornar o CEP do usuário quando todos os dados estão presentes")
    void should_return_zip_code_when_all_data_present() {
        UserProfileService.Address address = new UserProfileService.Address("01310-100");
        UserProfileService.Profile profile = new UserProfileService.Profile(address);
        UserProfileService.User user = new UserProfileService.User(profile);

        Optional<String> zip = service.findZipCode(user);

        assertTrue(zip.isPresent());
        assertEquals("01310-100", zip.get());
    }

    @Test
    @DisplayName("deve retornar Optional vazio quando o usuário é null")
    void should_return_empty_when_user_is_null() {
        Optional<String> zip = service.findZipCode(null);

        assertNotNull(zip);
        assertTrue(zip.isEmpty());
    }

    @Test
    @DisplayName("deve retornar Optional vazio quando o profile é null")
    void should_return_empty_when_profile_is_null() {
        UserProfileService.User user = new UserProfileService.User(null);

        Optional<String> zip = service.findZipCode(user);

        assertTrue(zip.isEmpty());
    }

    @Test
    @DisplayName("deve retornar Optional vazio quando o address é null")
    void should_return_empty_when_address_is_null() {
        UserProfileService.Profile profile = new UserProfileService.Profile(null);
        UserProfileService.User user = new UserProfileService.User(profile);

        Optional<String> zip = service.findZipCode(user);

        assertTrue(zip.isEmpty());
    }

    @Test
    @DisplayName("deve retornar valor padrão quando o CEP não pode ser obtido")
    void should_return_default_zip_when_chain_is_broken() {
        UserProfileService.User user = new UserProfileService.User(null);

        String zip = service.getZipCodeOrDefault(user, "00000-000");

        assertEquals("00000-000", zip);
    }
}
