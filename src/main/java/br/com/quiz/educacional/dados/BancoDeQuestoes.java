package br.com.quiz.educacional.dados;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.com.quiz.educacional.modelo.Questao;
import br.com.quiz.educacional.modelo.QuestaoDissertativa;
import br.com.quiz.educacional.modelo.QuestaoMultiplaEscolha;
import br.com.quiz.educacional.modelo.QuestaoVerdadeiroFalso;

/**
 * Armazena e organiza as questões disponíveis para o quiz.
 */
public class BancoDeQuestoes {
    private final List<Questao> questoes;

    public BancoDeQuestoes() {
        this.questoes = new ArrayList<>();
        adicionarQuestoesExemplo();
    }

    public void adicionarQuestao(Questao questao) {
        questoes.add(Objects.requireNonNull(questao, "A questão é obrigatória."));
    }

    /**
     * Retorna uma cópia imutável de todas as questões cadastradas.
     */
    public List<Questao> listarTodas() {
        return List.copyOf(questoes);
    }

    /**
     * Organiza a consulta pelo tipo concreto da questão.
     *
     * @param tipo classe do tipo de questão desejado
     * @param <T> tipo concreto que herda de {@link Questao}
     * @return lista imutável contendo somente questões do tipo informado
     */
    public <T extends Questao> List<T> listarPorTipo(Class<T> tipo) {
        Objects.requireNonNull(tipo, "O tipo da questão é obrigatório.");

        List<T> questoesDoTipo = new ArrayList<>();

        for (Questao questao : questoes) {
            if (tipo.isInstance(questao)) {
                questoesDoTipo.add(tipo.cast(questao));
            }
        }

        return List.copyOf(questoesDoTipo);
    }

    private void adicionarQuestoesExemplo() {
        adicionarQuestao(new QuestaoMultiplaEscolha(
                "Qual palavra-chave é usada para criar herança entre classes em Java?",
                List.of("implements", "extends", "inherits", "super"),
                1));

        adicionarQuestao(new QuestaoMultiplaEscolha(
                "Qual coleção Java permite armazenar elementos em sequência?",
                List.of("List", "Thread", "Scanner", "Exception"),
                0));

        adicionarQuestao(new QuestaoVerdadeiroFalso(
                "Em Java, todos os atributos de uma classe precisam ser públicos.",
                false));

        adicionarQuestao(new QuestaoVerdadeiroFalso(
                "O encapsulamento protege o estado interno de um objeto.",
                true));

        adicionarQuestao(new QuestaoDissertativa(
                "Qual é a sigla de Programação Orientada a Objetos?",
                "POO"));

        adicionarQuestao(new QuestaoDissertativa(
                "Qual método é o ponto de entrada de uma aplicação Java?",
                "main"));
    }
}
