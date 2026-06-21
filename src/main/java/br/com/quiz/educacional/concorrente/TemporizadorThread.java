package br.com.quiz.educacional.concorrente;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Cronômetro executado em paralelo ao fluxo principal do quiz.
 */
public class TemporizadorThread extends Thread {
    private final int segundosLimite;
    private final OuvinteDeTempoEsgotado ouvinte;
    private final AtomicBoolean cancelado = new AtomicBoolean(false);
    private final AtomicBoolean tempoEsgotado = new AtomicBoolean(false);

    public TemporizadorThread(int segundosLimite, OuvinteDeTempoEsgotado ouvinte) {
        if (segundosLimite <= 0) {
            throw new IllegalArgumentException("O limite de tempo deve ser positivo.");
        }

        this.segundosLimite = segundosLimite;
        this.ouvinte = Objects.requireNonNull(ouvinte, "O ouvinte é obrigatório.");
        setName("temporizador-quiz");
        setDaemon(true);
    }

    public void cancelar() {
        cancelado.set(true);
        interrupt();
    }

    public boolean foiTempoEsgotado() {
        return tempoEsgotado.get();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(segundosLimite * 1_000L);

            if (!cancelado.get()) {
                tempoEsgotado.set(true);
                ouvinte.aoTempoEsgotar();
            }
        } catch (InterruptedException excecao) {
            // O cancelamento após uma resposta interrompe o cronômetro.
            Thread.currentThread().interrupt();
        }
    }
}
