package br.com.quiz.educacional.modelo;

/**
 * Representa um participante do quiz.
 */
public class Usuario {
    private final String nome;

    public Usuario(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do usuário é obrigatório.");
        }

        this.nome = nome.trim();
    }

    public String getNome() {
        return nome;
    }
}
