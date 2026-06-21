package br.com.quiz.educacional.modelo;

/**
 * Representa a estrutura comum a todos os tipos de questão do quiz.
 */
public abstract class Questao {
    private final String enunciado;

    protected Questao(String enunciado) {
        if (enunciado == null || enunciado.isBlank()) {
            throw new IllegalArgumentException("O enunciado da questão é obrigatório.");
        }

        this.enunciado = enunciado.trim();
    }

    public String getEnunciado() {
        return enunciado;
    }

    /**
     * Verifica a resposta informada de acordo com o tipo concreto da questão.
     *
     * @param resposta resposta fornecida pelo jogador
     * @return {@code true} quando a resposta estiver correta
     */
    public abstract boolean verificarResposta(String resposta);
}
