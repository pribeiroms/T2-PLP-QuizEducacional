package br.com.quiz.educacional.modelo;

/**
 * Questão aberta corrigida pela comparação com uma resposta esperada.
 */
public class QuestaoDissertativa extends Questao {
    private final String respostaEsperada;

    public QuestaoDissertativa(String enunciado, String respostaEsperada) {
        super(enunciado);

        if (respostaEsperada == null || respostaEsperada.isBlank()) {
            throw new IllegalArgumentException("A resposta esperada é obrigatória.");
        }

        this.respostaEsperada = respostaEsperada.trim();
    }

    @Override
    public boolean verificarResposta(String resposta) {
        return resposta != null
                && respostaEsperada.equalsIgnoreCase(resposta.trim());
    }
}

