package io.skillforge.kata;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * KATA-001A: Virtual Threads — Hello Concurrency
 *
 * Implemente executeAll() usando Virtual Threads.
 *
 * Requisitos:
 *   - Use Executors.newVirtualThreadPerTaskExecutor()
 *   - Execute todas as tasks concorrentemente
 *   - Aguarde todas completarem antes de retornar a lista de resultados
 *   - NÃO use FixedThreadPool nem plataform threads explicitamente
 *
 * Dica: ExecutorService.invokeAll() pode ser seu melhor amigo aqui.
 */
public class ConcurrentExecutor {

    public <T> List<T> executeAll(List<Callable<T>> tasks) throws Exception {
        throw new UnsupportedOperationException("TODO: implement using Virtual Threads");
    }
}
