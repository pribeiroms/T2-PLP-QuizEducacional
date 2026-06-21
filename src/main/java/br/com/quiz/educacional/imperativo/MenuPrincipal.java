package br.com.quiz.educacional.imperativo;

import java.util.Scanner;

/**
 * Menu principal de navegação do Quiz Educacional.
 */
public class MenuPrincipal {
    private final Scanner scanner;
    private String ultimoResultado;

    public MenuPrincipal(Scanner scanner) {
        this.scanner = scanner;
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

            String opcao = scanner.nextLine().trim();

            switch (opcao) {
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
        System.out.println("Iniciando quiz...");
        ultimoResultado = null;
    }

    private void visualizarResultado() {
        if (ultimoResultado == null) {
            System.out.println("Nenhum resultado disponível.");
        } else {
            System.out.println(ultimoResultado);
        }
    }
}
