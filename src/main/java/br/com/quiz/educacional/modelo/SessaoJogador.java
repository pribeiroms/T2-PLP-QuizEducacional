package br.com.quiz.educacional.modelo;

import java.util.Objects;

/**
 * Relaciona o usuário ao quiz durante uma partida.
 */
public class SessaoJogador {
    private final Usuario usuario;
    private final Quiz quiz;
    private int pontuacao;
    private int sequenciaAcertos;
    private boolean finalizada;

    public SessaoJogador(Usuario usuario, Quiz quiz) {
        this.usuario = Objects.requireNonNull(usuario, "O usuário é obrigatório.");
        this.quiz = Objects.requireNonNull(quiz, "O quiz é obrigatório.");
        this.pontuacao = 0;
        this.sequenciaAcertos = 0;
        this.finalizada = false;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public int getSequenciaAcertos() {
        return sequenciaAcertos;
    }

    /**
     * Adiciona um ponto e registra mais um acerto consecutivo.
     */
    public void adicionarPontos() {
        adicionarPontos(1);
    }

    /**
     * Adiciona a quantidade informada e registra mais um acerto consecutivo.
     *
     * @param pontos quantidade positiva de pontos conquistados
     */
    public void adicionarPontos(int pontos) {
        if (pontos <= 0) {
            throw new IllegalArgumentException("A quantidade de pontos deve ser positiva.");
        }

        pontuacao += pontos;
        sequenciaAcertos++;
    }

    /**
     * Registra um erro e interrompe a sequência atual de acertos.
     */
    public void registrarErro() {
        sequenciaAcertos = 0;
    }

    /**
     * Reinicia a pontuação e a sequência de acertos da sessão.
     */
    public void zerarPontuacao() {
        pontuacao = 0;
        sequenciaAcertos = 0;
    }

    public boolean isFinalizada() {
        return finalizada;
    }

    public void finalizar() {
        finalizada = true;
    }
}
