package br.com.quiz.educacional;

import java.util.Scanner;

import br.com.quiz.educacional.imperativo.MenuPrincipal;

/**
 * Ponto de entrada do Quiz Educacional.
 */
public final class Main {
    private Main() {
        // Impede a instanciação da classe principal.
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            MenuPrincipal menu = new MenuPrincipal(scanner);
            menu.exibirMenu();
        }
    }
}
