package br.com.quiz.educacional;

import java.util.Scanner;

import br.com.quiz.educacional.concorrente.EntradaConsole;
import br.com.quiz.educacional.imperativo.MenuPrincipal;

/**
 * Ponto de entrada do Quiz Educacional.
 */
public final class Main {
    private Main() {
        // Impede a instanciação da classe principal.
    }

    public static void main(String[] args) {
        // System.in permanece aberto porque a leitura é feita por uma thread daemon.
        // Fechar o Scanner enquanto essa thread aguarda uma linha pode bloquear a saída.
        Scanner scanner = new Scanner(System.in);
        EntradaConsole entrada = new EntradaConsole(scanner);
        MenuPrincipal menu = new MenuPrincipal(entrada);
        menu.exibirMenu();
    }
}
