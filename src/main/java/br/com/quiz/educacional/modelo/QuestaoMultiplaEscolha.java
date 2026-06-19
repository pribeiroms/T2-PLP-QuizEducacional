package br.com.quiz.educacional.modelo;

import java.util.List;

/**
 * Questão que apresenta alternativas e possui apenas uma resposta correta.
 */
public class QuestaoMultiplaEscolha extends Questao {
    private final List<String> alternativas;
    private final int indiceRespostaCorreta;

    public QuestaoMultiplaEscolha(
            String enunciado,
            List<String> alternativas,
            int indiceRespostaCorreta) {
        super(enunciado);

        if (alternativas == null || alternativas.size() < 2) {
            throw new IllegalArgumentException("A questão deve possuir pelo menos duas alternativas.");
        }

        for (String alternativa : alternativas) {
            if (alternativa == null || alternativa.isBlank()) {
                throw new IllegalArgumentException("As alternativas não podem ser vazias.");
            }
        }

        if (indiceRespostaCorreta < 0 || indiceRespostaCorreta >= alternativas.size()) {
            throw new IllegalArgumentException("O índice da resposta correta é inválido.");
        }

        this.alternativas = alternativas.stream()
                .map(String::trim)
                .toList();
        this.indiceRespostaCorreta = indiceRespostaCorreta;
    }

    public List<String> getAlternativas() {
        return alternativas;
    }

    @Override
    public boolean verificarResposta(String resposta) {
        if (resposta == null || resposta.isBlank()) {
            return false;
        }

        String respostaNormalizada = resposta.trim();
        int indiceInformado = converterEmIndice(respostaNormalizada);

        if (indiceInformado >= 0) {
            return indiceInformado == indiceRespostaCorreta;
        }

        return alternativas.get(indiceRespostaCorreta).equalsIgnoreCase(respostaNormalizada);
    }

    private int converterEmIndice(String resposta) {
        try {
            return Integer.parseInt(resposta) - 1;
        } catch (NumberFormatException excecao) {
            if (resposta.length() == 1 && Character.isLetter(resposta.charAt(0))) {
                return Character.toUpperCase(resposta.charAt(0)) - 'A';
            }

            return -1;
        }
    }
}

