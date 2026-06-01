package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("JPMS: Criar e Consumir um Módulo - Testes")
class ModuleConsumerTest {

    private ModuleConsumer consumer;

    @BeforeEach
    void setUp() {
        consumer = new ModuleConsumer();
    }

    @Test
    @DisplayName("Deve retornar o nome do módulo atual")
    void should_return_current_module_name() {
        String moduleName = consumer.getCurrentModuleName();
        assertNotNull(moduleName, "O nome do módulo não deve ser nulo");
        assertFalse(moduleName.isEmpty(), "O nome do módulo não deve ser vazio");
    }

    @Test
    @DisplayName("Deve verificar se uma classe pertence a um módulo nomeado")
    void should_check_if_class_is_in_named_module() {
        assertTrue(consumer.isInNamedModule(String.class),
                "java.lang.String deve estar em um módulo nomeado (java.base)");
    }

    @Test
    @DisplayName("Deve retornar o nome do módulo de uma classe específica")
    void should_return_module_name_of_given_class() {
        String moduleName = consumer.getModuleNameOf(String.class);
        assertEquals("java.base", moduleName,
                "A classe String deve pertencer ao módulo java.base");
    }

    @Test
    @DisplayName("Deve listar os pacotes exportados consumidos")
    void should_list_consumed_packages() {
        List<String> packages = consumer.getConsumedPackages();
        assertNotNull(packages, "A lista de pacotes não deve ser nula");
        assertFalse(packages.isEmpty(), "Deve haver pelo menos um pacote consumido");
        assertTrue(packages.contains("java.lang") || packages.contains("java.util"),
                "Deve conter pacotes básicos como java.lang ou java.util");
    }

    @Test
    @DisplayName("Deve indicar se o módulo java.base está disponível")
    void should_confirm_java_base_module_is_available() {
        assertTrue(consumer.isModuleAvailable("java.base"),
                "O módulo java.base deve estar sempre disponível");
        assertFalse(consumer.isModuleAvailable("modulo.inexistente.xyz"),
                "Módulo inexistente não deve estar disponível");
    }
}
