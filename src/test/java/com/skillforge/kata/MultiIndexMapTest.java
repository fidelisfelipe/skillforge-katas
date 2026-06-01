package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes de MultiIndexMap - Algoritmos e Ordenação de Collections")
class MultiIndexMapTest {

    private MultiIndexMap map;

    @BeforeEach
    void setUp() {
        map = new MultiIndexMap();
    }

    @Test
    @DisplayName("deve adicionar valor a uma chave e recuperá-lo")
    void should_add_and_retrieve_values_by_key() {
        map.add("frutas", "maçã");
        map.add("frutas", "banana");
        List<String> result = map.get("frutas");
        assertEquals(2, result.size());
        assertTrue(result.contains("maçã"));
        assertTrue(result.contains("banana"));
    }

    @Test
    @DisplayName("deve retornar lista vazia quando a chave não existe")
    void should_return_empty_list_for_missing_key() {
        List<String> result = map.get("inexistente");
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("deve retornar valores ordenados para uma chave")
    void should_return_sorted_values_for_key() {
        map.add("letras", "charlie");
        map.add("letras", "alpha");
        map.add("letras", "bravo");
        List<String> sorted = map.getSorted("letras");
        assertEquals(List.of("alpha", "bravo", "charlie"), sorted);
    }

    @Test
    @DisplayName("deve contar o total de valores em todas as chaves")
    void should_count_total_values() {
        map.add("a", "x");
        map.add("a", "y");
        map.add("b", "z");
        assertEquals(3, map.totalSize());
    }

    @Test
    @DisplayName("deve retornar todas as chaves ordenadas")
    void should_return_sorted_keys() {
        map.add("zebra", "1");
        map.add("abelha", "2");
        map.add("macaco", "3");
        List<String> keys = map.sortedKeys();
        assertEquals(List.of("abelha", "macaco", "zebra"), keys);
    }
}
