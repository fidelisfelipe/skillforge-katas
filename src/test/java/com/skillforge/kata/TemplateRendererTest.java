package com.skillforge.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Text Blocks: Renderizador de Templates sem Escape Hell")
class TemplateRendererTest {

    private TemplateRenderer renderer;

    @BeforeEach
    void setUp() {
        renderer = new TemplateRenderer();
    }

    @Test
    @DisplayName("Deve renderizar HTML básico com título e corpo usando text block")
    void should_render_basic_html_with_title_and_body() {
        String html = renderer.renderHtml("Olá", "Mundo");
        assertNotNull(html, "O HTML renderizado não deve ser nulo");
        assertTrue(html.contains("<title>Olá</title>"), "O HTML deve conter o título dentro da tag <title>");
        assertTrue(html.contains("Mundo"), "O HTML deve conter o corpo informado");
        assertTrue(html.contains("<html>") && html.contains("</html>"),
                "O HTML deve conter as tags <html> e </html>");
    }

    @Test
    @DisplayName("Deve renderizar JSON com aspas duplas sem necessidade de escape excessivo")
    void should_render_json_with_double_quotes() {
        String json = renderer.renderJson("Ana", 30);
        assertNotNull(json, "O JSON renderizado não deve ser nulo");
        assertTrue(json.contains("\"name\""), "O JSON deve conter a chave \"name\"");
        assertTrue(json.contains("\"Ana\""), "O JSON deve conter o valor \"Ana\"");
        assertTrue(json.contains("\"age\""), "O JSON deve conter a chave \"age\"");
        assertTrue(json.contains("30"), "O JSON deve conter o valor 30");
        assertTrue(json.trim().startsWith("{") && json.trim().endsWith("}"),
                "O JSON deve começar com { e terminar com }");
    }

    @Test
    @DisplayName("Deve renderizar consulta SQL multilinha preservando palavras-chave")
    void should_render_multiline_sql_query() {
        String sql = renderer.renderSqlQuery("users", "active");
        assertNotNull(sql, "A consulta SQL não deve ser nula");
        String upper = sql.toUpperCase();
        assertTrue(upper.contains("SELECT"), "A consulta deve conter SELECT");
        assertTrue(upper.contains("FROM"), "A consulta deve conter FROM");
        assertTrue(upper.contains("WHERE"), "A consulta deve conter WHERE");
        assertTrue(sql.contains("users"), "A consulta deve conter o nome da tabela 'users'");
        assertTrue(sql.contains("active"), "A consulta deve conter a condição 'active'");
        assertTrue(sql.contains("\n"), "A consulta SQL deve ser multilinha (conter quebras de linha)");
    }

    @Test
    @DisplayName("Deve substituir placeholders no template usando formatted")
    void should_replace_placeholders_in_template() {
        String result = renderer.greet("Carlos", "Brasil");
        assertNotNull(result, "O texto renderizado não deve ser nulo");
        assertTrue(result.contains("Carlos"), "O resultado deve conter o nome 'Carlos'");
        assertTrue(result.contains("Brasil"), "O resultado deve conter o país 'Brasil'");
        assertFalse(result.contains("%s"), "O resultado não deve conter placeholders %s não substituídos");
    }

    @Test
    @DisplayName("Deve produzir text block sem indentação incidental à esquerda")
    void should_strip_incidental_whitespace() {
        String json = renderer.renderJson("Beto", 25);
        String[] lines = json.split("\n");
        boolean hasNonIndentedLine = false;
        for (String line : lines) {
            if (!line.isEmpty() && !line.startsWith(" ") && !line.startsWith("\t")) {
                hasNonIndentedLine = true;
                break;
            }
        }
        assertTrue(hasNonIndentedLine,
                "Pelo menos uma linha do text block deve estar sem indentação incidental (uso correto de \"\"\")");
    }
}
