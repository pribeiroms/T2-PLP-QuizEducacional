package br.com.quiz.educacional.modelo;

import java.util.Objects;

/**
 * Relaciona o usuário ao quiz durante uma partida.
 */
public class SessaoJogador {
    private final Usuario usuario;
    private final Quiz quiz;
    private boolean finalizada;

    public SessaoJogador(Usuario usuario, Quiz quiz) {
        this.usuario = Objects.requireNonNull(usuario, "O usuário é obrigatório.");
        this.quiz = Objects.requireNonNull(quiz, "O quiz é obrigatório.");
        this.finalizada = false;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public boolean isFinalizada() {
        return finalizada;
    }

    public void finalizar() {
        finalizada = true;
    }
}

