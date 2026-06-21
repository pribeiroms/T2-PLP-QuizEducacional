package br.com.quiz.educacional.imperativo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.quiz.educacional.dados.BancoDeQuestoes;
import br.com.quiz.educacional.funcional.CorretorAutomatico;
import br.com.quiz.educacional.modelo.Questao;
import br.com.quiz.educacional.modelo.QuestaoMultiplaEscolha;
import br.com.quiz.educacional.modelo.QuestaoVerdadeiroFalso;
import br.com.quiz.educacional.modelo.SessaoJogador;
import br.com.quiz.educacional.relatorio.RelatorioDesempenho;

/**
 * Controla imperativamente a exibição, a leitura e a correção do quiz.
 */
public class GameLoop {
    private static final int PONTOS_POR_ACERTO = 10;

    private final Scanner scanner;
    private final SessaoJogador sessao;

    public GameLoop(Scanner scanner, SessaoJogador sessao) {
        this.scanner = scanner;
        this.sessao = sessao;
    }

    public RelatorioDesempenho iniciar() {
        List<Questao> questoes = new BancoDeQuestoes().listarTodas();
        List<String> respostas = new ArrayList<>();

        System.out.println();
        System.out.println("Iniciando o quiz, " + sessao.getUsuario().getNome() + "!");

        for (int indice = 0; indice < questoes.size(); indice++) {
            Questao questao = questoes.get(indice);
            System.out.printf("%n--- Questão %d de %d ---%n", indice + 1, questoes.size());
            exibirQuestao(questao);

            System.out.print("Sua resposta: ");
            String resposta = scanner.nextLine();
            respostas.add(resposta);
            processarResposta(questao, resposta);
        }

        sessao.finalizar();
        CorretorAutomatico corretor = new CorretorAutomatico(questoes, respostas);
        RelatorioDesempenho relatorio = new RelatorioDesempenho(corretor);

        System.out.println();
        System.out.println("Pontuação: " + sessao.getPontuacao());
        relatorio.exibir();
        return relatorio;
    }

    private void exibirQuestao(Questao questao) {
        System.out.println(questao.getEnunciado());

        if (questao instanceof QuestaoMultiplaEscolha multiplaEscolha) {
            List<String> alternativas = multiplaEscolha.getAlternativas();
            for (int indice = 0; indice < alternativas.size(); indice++) {
                char letra = (char) ('A' + indice);
                System.out.println(letra + ") " + alternativas.get(indice));
            }
        } else if (questao instanceof QuestaoVerdadeiroFalso) {
            System.out.println("Responda com V (verdadeiro) ou F (falso).");
        }
    }

    private void processarResposta(Questao questao, String resposta) {
        if (questao.verificarResposta(resposta)) {
            sessao.adicionarPontos(PONTOS_POR_ACERTO);
            System.out.println("Resultado: correto! +" + PONTOS_POR_ACERTO + " pontos.");
        } else {
            sessao.registrarErro();
            System.out.println("Resultado: incorreto.");
        }
    }
}
