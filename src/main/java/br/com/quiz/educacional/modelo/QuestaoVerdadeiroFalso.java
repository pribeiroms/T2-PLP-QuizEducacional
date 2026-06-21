package br.com.quiz.educacional.modelo;

/**
 * Questão cuja resposta pode ser verdadeira ou falsa.
 */
public class QuestaoVerdadeiroFalso extends Questao {
    private final boolean respostaCorreta;

    public QuestaoVerdadeiroFalso(String enunciado, boolean respostaCorreta) {
        super(enunciado);
        this.respostaCorreta = respostaCorreta;
    }

    @Override
    public boolean verificarResposta(String resposta) {
        if (resposta == null) {
            return false;
        }

        String respostaNormalizada = resposta.trim().toLowerCase();

        return switch (respostaNormalizada) {
            case "verdadeiro", "v", "true" -> respostaCorreta;
            case "falso", "f", "false" -> !respostaCorreta;
            default -> false;
        };
    }
}
