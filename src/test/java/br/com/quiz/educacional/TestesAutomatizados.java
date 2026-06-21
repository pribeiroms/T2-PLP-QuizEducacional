package br.com.quiz.educacional;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import br.com.quiz.educacional.concorrente.TemporizadorThread;
import br.com.quiz.educacional.dados.BancoDeQuestoes;
import br.com.quiz.educacional.funcional.CorretorAutomatico;
import br.com.quiz.educacional.modelo.Questao;
import br.com.quiz.educacional.modelo.QuestaoDissertativa;
import br.com.quiz.educacional.modelo.QuestaoMultiplaEscolha;
import br.com.quiz.educacional.modelo.QuestaoVerdadeiroFalso;
import br.com.quiz.educacional.modelo.Quiz;
import br.com.quiz.educacional.modelo.SessaoJogador;
import br.com.quiz.educacional.modelo.Usuario;
import br.com.quiz.educacional.relatorio.RelatorioDesempenho;

/**
 * Testes executáveis apenas com o JDK, sem dependências externas.
 */
public final class TestesAutomatizados {
    private TestesAutomatizados() {
    }

    public static void main(String[] args) throws InterruptedException {
        testarTiposDeQuestao();
        testarPontuacao();
        testarBancoDeQuestoes();
        testarCorrecaoERelatorio();
        testarTemporizador();
        System.out.println("Todos os testes passaram.");
    }

    private static void testarTiposDeQuestao() {
        QuestaoMultiplaEscolha multiplaEscolha = new QuestaoMultiplaEscolha(
                "Qual é a segunda alternativa?", List.of("A", "B", "C"), 1);
        verificar(multiplaEscolha.verificarResposta("B"), "Deve aceitar a letra da alternativa.");
        verificar(multiplaEscolha.verificarResposta("2"), "Deve aceitar o número da alternativa.");
        verificar(!multiplaEscolha.verificarResposta("A"), "Deve rejeitar alternativa incorreta.");

        Questao verdadeiroFalso = new QuestaoVerdadeiroFalso("Java é uma linguagem?", true);
        verificar(verdadeiroFalso.verificarResposta("V"), "Deve aceitar verdadeiro abreviado.");
        verificar(!verdadeiroFalso.verificarResposta("F"), "Deve rejeitar falso quando a resposta é verdadeira.");

        Questao dissertativa = new QuestaoDissertativa("Sigla de POO?", "POO");
        verificar(dissertativa.verificarResposta(" poo "), "Deve ignorar espaços e caixa na resposta aberta.");
    }

    private static void testarPontuacao() {
        SessaoJogador sessao = new SessaoJogador(new Usuario("Teste"), new Quiz("Quiz"));
        sessao.adicionarPontos(10);
        sessao.adicionarPontos(10);
        verificar(sessao.getPontuacao() == 20, "A pontuação deve acumular.");
        verificar(sessao.getSequenciaAcertos() == 2, "A sequência de acertos deve aumentar.");

        sessao.registrarErro();
        verificar(sessao.getSequenciaAcertos() == 0, "Um erro deve interromper a sequência.");
        sessao.zerarPontuacao();
        verificar(sessao.getPontuacao() == 0, "A pontuação deve ser zerada.");

        sessao.finalizar();
        verificar(sessao.isFinalizada(), "A sessão deve poder ser finalizada.");
    }

    private static void testarBancoDeQuestoes() {
        BancoDeQuestoes banco = new BancoDeQuestoes();
        verificar(banco.listarTodas().size() == 6, "O banco deve carregar seis questões de exemplo.");
        verificar(banco.listarPorTipo(QuestaoMultiplaEscolha.class).size() == 2,
                "O banco deve organizar questões de múltipla escolha.");
        verificar(banco.listarPorTipo(QuestaoVerdadeiroFalso.class).size() == 2,
                "O banco deve organizar questões de verdadeiro ou falso.");
        verificar(banco.listarPorTipo(QuestaoDissertativa.class).size() == 2,
                "O banco deve organizar questões dissertativas.");
    }

    private static void testarCorrecaoERelatorio() {
        List<Questao> questoes = List.of(
                new QuestaoDissertativa("Resposta um", "certo"),
                new QuestaoVerdadeiroFalso("Resposta dois", true));
        CorretorAutomatico corretor = new CorretorAutomatico(questoes, List.of("certo", "F"));

        verificar(corretor.contarAcertos() == 1, "A correção deve contar um acerto.");
        verificar(proximo(corretor.calcularPercentualAproveitamento(), 50.0),
                "O percentual deve ser 50%.");
        verificar(proximo(corretor.calcularNotaFinal(10.0), 5.0), "A nota final deve ser 5.");
        verificar(corretor.listarQuestoesErradas().size() == 1, "Deve listar uma questão errada.");
        verificar(corretor.listarEnunciadosCorretos().equals(List.of("Resposta um")),
                "Deve mapear o enunciado correto.");
        verificar(corretor.contarPorTipo(QuestaoVerdadeiroFalso.class) == 1,
                "Deve contar questões por tipo.");

        RelatorioDesempenho relatorio = new RelatorioDesempenho(corretor);
        verificar(relatorio.getTotalRespondidas() == 2, "O relatório deve mostrar duas questões.");
        verificar(relatorio.getAcertos() == 1, "O relatório deve mostrar um acerto.");
        verificar(relatorio.getErros() == 1, "O relatório deve mostrar um erro.");
    }

    private static void testarTemporizador() throws InterruptedException {
        AtomicBoolean disparou = new AtomicBoolean(false);
        TemporizadorThread temporizador = new TemporizadorThread(1, () -> disparou.set(true));
        temporizador.start();
        temporizador.join(1_500);

        verificar(disparou.get(), "O temporizador deve disparar ao esgotar o tempo.");
        verificar(temporizador.foiTempoEsgotado(), "O temporizador deve registrar o esgotamento.");
        verificar(!temporizador.isAlive(), "A thread deve encerrar após disparar.");

        AtomicBoolean canceladoDisparou = new AtomicBoolean(false);
        TemporizadorThread cancelado = new TemporizadorThread(1, () -> canceladoDisparou.set(true));
        cancelado.start();
        cancelado.cancelar();
        cancelado.join(500);
        verificar(!canceladoDisparou.get(), "Um temporizador cancelado não deve disparar.");
    }

    private static boolean proximo(double valor, double esperado) {
        return Math.abs(valor - esperado) < 0.0001;
    }

    private static void verificar(boolean condicao, String mensagem) {
        if (!condicao) {
            throw new AssertionError(mensagem);
        }
    }
}
