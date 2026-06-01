package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("LibraryCatalog: flatMap e composição de pipelines")
class LibraryCatalogTest {

    private LibraryCatalog catalog;

    record Book(String title, List<String> authors, List<String> tags) {}

    private List<Book> books;

    @BeforeEach
    void setUp() {
        catalog = new LibraryCatalog();
        books = List.of(
                new Book("Effective Java", List.of("Joshua Bloch"), List.of("java", "best-practices")),
                new Book("Java Concurrency in Practice", List.of("Brian Goetz", "Joshua Bloch"), List.of("java", "concurrency")),
                new Book("Clean Code", List.of("Robert Martin"), List.of("best-practices", "clean"))
        );
    }

    @Test
    @DisplayName("deve retornar todos os autores únicos via flatMap")
    void should_return_all_unique_authors() {
        Set<String> authors = catalog.allAuthors(books);
        assertEquals(Set.of("Joshua Bloch", "Brian Goetz", "Robert Martin"), authors);
    }

    @Test
    @DisplayName("deve retornar todas as tags únicas")
    void should_return_all_unique_tags() {
        Set<String> tags = catalog.allTags(books);
        assertEquals(Set.of("java", "best-practices", "concurrency", "clean"), tags);
    }

    @Test
    @DisplayName("deve encontrar títulos por tag")
    void should_find_titles_by_tag() {
        List<String> titles = catalog.titlesWithTag(books, "java");
        assertEquals(2, titles.size());
        assertTrue(titles.contains("Effective Java"));
        assertTrue(titles.contains("Java Concurrency in Practice"));
    }

    @Test
    @DisplayName("deve contar quantos livros cada autor escreveu")
    void should_count_books_per_author() {
        assertEquals(2L, catalog.bookCountForAuthor(books, "Joshua Bloch"));
        assertEquals(1L, catalog.bookCountForAuthor(books, "Brian Goetz"));
        assertEquals(0L, catalog.bookCountForAuthor(books, "Unknown"));
    }

    @Test
    @DisplayName("deve retornar conjuntos vazios quando o catálogo está vazio")
    void should_return_empty_when_no_books() {
        assertTrue(catalog.allAuthors(List.of()).isEmpty());
        assertTrue(catalog.allTags(List.of()).isEmpty());
    }
}
