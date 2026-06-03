package com.skillforge.kata;

import com.skillforge.kata.IssueTriager.Category;
import com.skillforge.kata.IssueTriager.IssueInput;
import com.skillforge.kata.IssueTriager.TriageResult;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@DisplayName("KATA-AI01A: IssueTriager — Integração com Ollama local")
class IssueTriagerIT {

    private static final String OLLAMA_URL = "http://localhost:11434";
    private static final String MODEL = "llama3.2";
    private static IssueTriager triager;

    private static String skipReason = null;

    @BeforeAll
    static void verificarOllama() {
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(3))
                .build();

        String tagsBody;
        try {
            HttpResponse<String> resp = client.send(
                    HttpRequest.newBuilder().uri(URI.create(OLLAMA_URL + "/api/tags")).GET().build(),
                    HttpResponse.BodyHandlers.ofString());
            if (resp.statusCode() != 200) {
                skipReason = "Ollama não está rodando em " + OLLAMA_URL + " — inicie com: ollama serve";
                return;
            }
            tagsBody = resp.body();
        } catch (Exception e) {
            skipReason = "Ollama não está rodando em " + OLLAMA_URL + " — inicie com: ollama serve";
            return;
        }

        if (!tagsBody.contains(MODEL)) {
            skipReason = "Modelo '" + MODEL + "' não encontrado — instale com: ollama pull " + MODEL;
            return;
        }

        triager = new IssueTriager();
    }

    @BeforeEach
    void assumirPreRequisitos() {
        assumeTrue(skipReason == null, skipReason);
    }

    @Test
    @DisplayName("Bug report claro → resultado válido, não fallback")
    void triage_bugReport_retornaResultadoValido() {
        TriageResult result = triager.triage(new IssueInput(
                "NullPointerException ao fazer login com email vazio",
                "Ao tentar logar sem preencher o campo de email, o sistema lança " +
                "NullPointerException e apresenta uma tela de erro genérica. " +
                "Reproduzido em 100% das tentativas na versão 2.3.1."
        ));

        assertNotNull(result.category(), "category não pode ser nula");
        assertNotNull(result.priority(), "priority não pode ser nula");
        assertNotNull(result.suggestedLabel(), "suggestedLabel não pode ser nulo");
        assertFalse(result.suggestedLabel().isBlank(), "suggestedLabel não pode ser vazio");

        assertNotEquals("needs-triage", result.suggestedLabel(),
                "LLM retornou fallback — verifique se o modelo está respondendo JSON corretamente");

        assertEquals(Category.BUG, result.category(),
                "Bug report óbvio deveria ser classificado como BUG, mas foi: " + result.category());
    }

    @Test
    @DisplayName("Feature request → não deve ser classificado como BUG")
    void triage_featureRequest_naoEhBug() {
        TriageResult result = triager.triage(new IssueInput(
                "Adicionar suporte a dark mode",
                "Gostaríamos de ter um modo escuro nas configurações do perfil. " +
                "Muito pedido pela comunidade, especialmente para uso noturno."
        ));

        assertNotNull(result.category());
        assertNotNull(result.suggestedLabel());
        assertNotEquals("needs-triage", result.suggestedLabel(),
                "LLM retornou fallback — verifique resposta do modelo");

        assertNotEquals(Category.BUG, result.category(),
                "Feature request não deve ser classificada como BUG");
    }

    @Test
    @DisplayName("Pergunta de uso → não deve retornar fallback")
    void triage_perguntaDeUso_retornaResultadoValido() {
        TriageResult result = triager.triage(new IssueInput(
                "Como configurar o timeout de sessão?",
                "Não encontrei na documentação como ajustar o timeout para usuários inativos. " +
                "Preciso definir 30 minutos. Qual propriedade devo usar?"
        ));

        assertNotNull(result.category());
        assertNotNull(result.priority());
        assertFalse(result.suggestedLabel().isBlank());
        assertNotEquals("needs-triage", result.suggestedLabel(),
                "LLM retornou fallback — verifique resposta do modelo");
    }
}
