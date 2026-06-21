package br.com.quiz.educacional.concorrente;

import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BooleanSupplier;

/**
 * Centraliza a leitura do console em uma única thread.
 *
 * <p>Como {@link Scanner#nextLine()} é bloqueante, a fila permite que o fluxo do
 * quiz pare de esperar uma resposta assim que o temporizador disparar.</p>
 */
public final class EntradaConsole {
    private final BlockingQueue<String> linhas = new LinkedBlockingQueue<>();
    private final AtomicBoolean encerrada = new AtomicBoolean(false);

    public EntradaConsole(Scanner scanner) {
        Objects.requireNonNull(scanner, "O scanner é obrigatório.");

        Thread leitora = new Thread(() -> lerContinuamente(scanner), "leitor-console");
        leitora.setDaemon(true);
        leitora.start();
    }

    private void lerContinuamente(Scanner scanner) {
        try {
            while (scanner.hasNextLine()) {
                linhas.put(scanner.nextLine());
            }
        } catch (InterruptedException excecao) {
            Thread.currentThread().interrupt();
        } catch (IllegalStateException excecao) {
            // O Scanner foi fechado durante o encerramento normal do programa.
        } finally {
            encerrada.set(true);
        }
    }

    public String lerLinha() {
        return lerLinhaEnquanto(() -> true);
    }

    /**
     * Aguarda uma linha somente enquanto a condição informada for verdadeira.
     */
    public String lerLinhaEnquanto(BooleanSupplier continuarAguardando) {
        Objects.requireNonNull(continuarAguardando, "A condição é obrigatória.");

        try {
            while (continuarAguardando.getAsBoolean()) {
                String linha = linhas.poll(50, TimeUnit.MILLISECONDS);
                if (linha != null) {
                    return linha;
                }

                if (encerrada.get() && linhas.isEmpty()) {
                    return null;
                }
            }
        } catch (InterruptedException excecao) {
            Thread.currentThread().interrupt();
        }

        return null;
    }
}
