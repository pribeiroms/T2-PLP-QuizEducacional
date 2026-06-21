package br.com.quiz.educacional.imperativo;

import br.com.quiz.educacional.concorrente.EntradaConsole;
import br.com.quiz.educacional.modelo.Quiz;
import br.com.quiz.educacional.modelo.SessaoJogador;
import br.com.quiz.educacional.modelo.Usuario;
import br.com.quiz.educacional.relatorio.RelatorioDesempenho;

/**
 * Menu de navegação do Quiz Educacional.
 */
public class MenuPrincipal {
    private final EntradaConsole entrada;
    private RelatorioDesempenho ultimoResultado;

    public MenuPrincipal(EntradaConsole entrada) {
        this.entrada = entrada;
    }

    public void exibirMenu() {
        boolean continuar = true;

        while (continuar) {
            System.out.println();
            System.out.println("====================================");
            System.out.println("   QUIZ EDUCACIONAL - MENU PRINCIPAL");
            System.out.println("====================================");
            System.out.println("1 - Iniciar quiz");
            System.out.println("2 - Visualizar último resultado");
            System.out.println("3 - Sair");
            System.out.print("Escolha uma opção: ");

            String opcao = entrada.lerLinha();
            if (opcao == null) {
                return;
            }

            switch (opcao.trim()) {
                case "1" -> iniciarQuiz();
                case "2" -> visualizarResultado();
                case "3" -> {
                    System.out.println("Saindo do sistema. Até a próxima!");
                    continuar = false;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void iniciarQuiz() {
        System.out.print("Digite seu nome: ");
        String nome = entrada.lerLinha();

        if (nome == null || nome.isBlank()) {
            nome = "Jogador";
        }

        Usuario usuario = new Usuario(nome);
        Quiz quiz = new Quiz("Quiz Educacional");
        SessaoJogador sessao = new SessaoJogador(usuario, quiz);
        GameLoop gameLoop = new GameLoop(entrada, sessao);
        ultimoResultado = gameLoop.iniciar();
    }

    private void visualizarResultado() {
        if (ultimoResultado == null) {
            System.out.println("Nenhum quiz foi concluído nesta execução.");
        } else {
            ultimoResultado.exibir();
        }
    }
}
