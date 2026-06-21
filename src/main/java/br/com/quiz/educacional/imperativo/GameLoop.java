package br.com.quiz.educacional.imperativo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import br.com.quiz.educacional.concorrente.EntradaConsole;
import br.com.quiz.educacional.concorrente.OuvinteDeTempoEsgotado;
import br.com.quiz.educacional.concorrente.TemporizadorThread;
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
    private static final int SEGUNDOS_POR_QUESTAO = 20;
    private static final int PONTOS_POR_ACERTO = 10;

    private final EntradaConsole entrada;
    private final SessaoJogador sessao;

    public GameLoop(EntradaConsole entrada, SessaoJogador sessao) {
        this.entrada = entrada;
        this.sessao = sessao;
    }

    public RelatorioDesempenho iniciar() {
        List<Questao> questoes = new BancoDeQuestoes().listarTodas();
        List<String> respostas = new ArrayList<>();

        System.out.println();
        System.out.println("Iniciando o desafio, " + sessao.getUsuario().getNome() + "!");
        System.out.println("Você terá " + SEGUNDOS_POR_QUESTAO + " segundos por questão.");

        for (int indice = 0; indice < questoes.size(); indice++) {
            Questao questao = questoes.get(indice);
            System.out.printf("%n--- Questão %d de %d ---%n", indice + 1, questoes.size());
            exibirQuestao(questao);

            String resposta = lerRespostaComTemporizador();
            respostas.add(resposta == null ? "" : resposta);
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

    private String lerRespostaComTemporizador() {
        AtomicBoolean tempoEsgotado = new AtomicBoolean(false);
        OuvinteDeTempoEsgotado ouvinte = () -> {
            tempoEsgotado.set(true);
            System.out.println();
            System.out.println(">>> TEMPO ESGOTADO! Avançando para a próxima questão...");
        };

        TemporizadorThread temporizador =
                new TemporizadorThread(SEGUNDOS_POR_QUESTAO, ouvinte);
        temporizador.start();

        System.out.print("Sua resposta: ");
        String resposta = entrada.lerLinhaEnquanto(() -> !tempoEsgotado.get());

        if (!tempoEsgotado.get()) {
            temporizador.cancelar();
        }

        return tempoEsgotado.get() ? null : resposta;
    }

    private void processarResposta(Questao questao, String resposta) {
        if (resposta == null) {
            sessao.registrarErro();
            System.out.println("Resultado: questão não respondida dentro do limite.");
        } else if (questao.verificarResposta(resposta)) {
            sessao.adicionarPontos(PONTOS_POR_ACERTO);
            System.out.println("Resultado: correto! +" + PONTOS_POR_ACERTO + " pontos.");
        } else {
            sessao.registrarErro();
            System.out.println("Resultado: incorreto.");
        }
    }
}
