# Pré-requisitos — Testes de Integração (IssueTriagerIT)

Os testes de integração chamam um LLM local via Ollama. Eles são **pulados automaticamente**
(`Skipped`) se o Ollama não estiver rodando ou o modelo não estiver instalado.

---

## 1. Instalar o Ollama

| Sistema | Comando / Link                                        |
|---------|-------------------------------------------------------|
| Windows | Baixe o instalador em https://ollama.com/download     |
| macOS   | `brew install ollama`                                 |
| Linux   | `curl -fsSL https://ollama.com/install.sh \| sh`      |

Verifique a instalação:

```bash
ollama --version
```

---

## 2. Baixar o modelo

```bash
ollama pull llama3.2
```

> Download de ~2 GB. Feito uma única vez — fica em cache local.

---

## 3. Iniciar o servidor

```bash
ollama serve
```

O servidor sobe em `http://localhost:11434`. Deixe este terminal aberto enquanto roda os testes.

---

## 4. Rodar os testes de integração

```bash
mvn verify
```

Saída esperada com Ollama rodando:

```
[INFO] Running com.skillforge.kata.IssueTriagerIT
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0
```

Saída esperada **sem** Ollama (skip automático):

```
[WARNING] Tests run: 3, Failures: 0, Errors: 0, Skipped: 3
```

---

## Referência rápida

| Comando | O que faz |
|---|---|
| `ollama pull llama3.2` | Baixa o modelo |
| `ollama serve` | Inicia o servidor na porta 11434 |
| `ollama list` | Lista modelos instalados |
| `ollama run llama3.2` | Conversa interativa com o modelo (teste manual) |
| `mvn verify` | Roda unitários + integração |
| `mvn test` | Roda apenas unitários (sem Ollama) |
