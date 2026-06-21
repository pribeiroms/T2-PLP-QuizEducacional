package br.com.quiz.educacional.funcional;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import br.com.quiz.educacional.modelo.Questao;

/**
 * Corrige as respostas de um jogador comparando-as com as questões
 * respondidas, utilizando Streams (Programação Funcional).
 */
public class CorretorAutomatico {
    private final List<Questao> questoes;
    private final List<String> respostas;

    public CorretorAutomatico(List<Questao> questoes, List<String> respostas) {
        Objects.requireNonNull(questoes, "A lista de questões é obrigatória.");
        Objects.requireNonNull(respostas, "A lista de respostas é obrigatória.");

        if (questoes.size() != respostas.size()) {
            throw new IllegalArgumentException(
                    "A quantidade de respostas deve ser igual à quantidade de questões.");
        }

        this.questoes = List.copyOf(questoes);
        this.respostas = List.copyOf(respostas);
    }

    public int getTotalQuestoes() {
        return questoes.size();
    }

    /**
     * Conta quantas respostas estão corretas usando Stream API.
     */
    public long contarAcertos() {
        return IntStream.range(0, questoes.size())
                .filter(i -> questoes.get(i).verificarResposta(respostas.get(i)))
                .count();
    }

    /**
     * Calcula o percentual de aproveitamento (0 a 100).
     */
    public double calcularPercentualAproveitamento() {
        if (questoes.isEmpty()) {
            return 0.0;
        }

        return (contarAcertos() * 100.0) / questoes.size();
    }

    /**
     * Calcula a nota final proporcional ao percentual de aproveitamento.
     *
     * @param notaMaxima valor máximo possível (ex.: 10.0)
     */
    public double calcularNotaFinal(double notaMaxima) {
        if (notaMaxima < 0) {
            throw new IllegalArgumentException("A nota máxima não pode ser negativa.");
        }

        return (calcularPercentualAproveitamento() / 100.0) * notaMaxima;
    }

    /**
     * Lista as questões cuja resposta foi incorreta.
     * Demonstra o uso de filter() + map() em conjunto.
     */
    public List<Questao> listarQuestoesErradas() {
        return IntStream.range(0, questoes.size())
                .filter(i -> !questoes.get(i).verificarResposta(respostas.get(i)))
                .mapToObj(questoes::get)
                .toList();
    }

    /**
     * Lista os enunciados das questões respondidas corretamente.
     * Demonstra map() transformando Questao em String.
     */
    public List<String> listarEnunciadosCorretos() {
        return IntStream.range(0, questoes.size())
                .filter(i -> questoes.get(i).verificarResposta(respostas.get(i)))
                .mapToObj(questoes::get)
                .map(Questao::getEnunciado)
                .toList();
    }

    /**
     * Conta quantas questões respondidas são de um determinado tipo.
     * Demonstra filter() + count() sobre o stream de questões.
     *
     * @param tipo classe do tipo de questão (ex.: QuestaoVerdadeiroFalso.class)
     */
    public long contarPorTipo(Class<? extends Questao> tipo) {
        Objects.requireNonNull(tipo, "O tipo da questão é obrigatório.");

        return questoes.stream()
                .filter(tipo::isInstance)
                .count();
    }
}
