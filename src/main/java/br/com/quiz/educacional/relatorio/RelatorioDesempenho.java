package br.com.quiz.educacional.relatorio;

import java.util.Objects;

import br.com.quiz.educacional.funcional.CorretorAutomatico;

/**
 * Monta e exibe o relatório de desempenho do jogador a partir
 * do resultado da correção automática.
 */
public class RelatorioDesempenho {
    private final CorretorAutomatico corretor;

    public RelatorioDesempenho(CorretorAutomatico corretor) {
        this.corretor = Objects.requireNonNull(corretor, "O corretor é obrigatório.");
    }

    public int getTotalRespondidas() {
        return corretor.getTotalQuestoes();
    }

    public long getAcertos() {
        return corretor.contarAcertos();
    }

    public long getErros() {
        return getTotalRespondidas() - getAcertos();
    }

    public double getPercentualAproveitamento() {
        return corretor.calcularPercentualAproveitamento();
    }

    /**
     * Monta o texto do relatório.
     */
    public String gerar() {
        return """
                ===== Relatório de Desempenho =====
                Total de questões respondidas: %d
                Acertos: %d
                Erros: %d
                Percentual de aproveitamento: %.1f%%
                ====================================
                """.formatted(getTotalRespondidas(), getAcertos(), getErros(), getPercentualAproveitamento());
    }

    /**
     * Imprime o relatório no console.
     */
    public void exibir() {
        System.out.println(gerar());
    }
}
