package br.com.quiz.educacional.concorrente;

/**
 * Recebe o evento disparado quando o tempo de uma questão se esgota.
 */
@FunctionalInterface
public interface OuvinteDeTempoEsgotado {
    void aoTempoEsgotar();
}
