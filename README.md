# KATA-001A: Virtual Threads — Hello Concurrency

**Dificuldade:** Intermediate | **XP:** 80

## Objetivo

Substitua um `FixedThreadPool` de 10 threads por Virtual Threads e demonstre que
10.000 tarefas concorrentes completam sem `OutOfMemoryError`.

## O que implementar

Abra `src/main/java/com/skillforge/kata/ConcurrentExecutor.java` e implemente o método:

```java
public <T> List<T> executeAll(List<Callable<T>> tasks) throws Exception
```

**Requisitos:**
- Use `Executors.newVirtualThreadPerTaskExecutor()`
- Execute todas as tasks **concorrentemente**
- Aguarde todas completarem antes de retornar
- Não use `FixedThreadPool` nem crie platform threads diretamente

## Como rodar

```bash
# Rodar os testes (devem passar todos)
mvn verify

# Se quiser ver o teste falhar antes de implementar
mvn test
```

## Critérios de aceite

| Teste | Descrição |
|---|---|
| `shouldExecute10000TasksWithoutOOM` | 10k tasks completam, sem erro |
| `shouldCompleteFasterThanPlatformThreadPool` | 1k tasks em < 2s |
| `tasksMustRunOnVirtualThreads` | `Thread.currentThread().isVirtual() == true` |
| `shouldHandleEmptyTaskList` | Input vazio retorna lista vazia |

## Submeter

Quando `mvn verify` estiver verde:

```bash
# Substitua {heroId} pelo seu heroId (ex: hero-template)
git checkout -b kata-001a-{heroId}-solution
git add .
git commit -m "kata-001a: implement ConcurrentExecutor with Virtual Threads"
git push origin kata-001a-{heroId}-solution
```

Abra um PR com o seguinte body (obrigatório para o hub identificar você):

```
heroId: {heroId}
```

O hub valida automaticamente ao receber o PR.
