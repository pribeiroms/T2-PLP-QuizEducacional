package br.com.quiz.educacional.modelo;

/**
 * Representa um quiz que poderá receber questões nas próximas etapas.
 */
public class Quiz {
    private final String titulo;

    public Quiz(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("O título do quiz é obrigatório.");
        }

        this.titulo = titulo.trim();
    }

    public String getTitulo() {
        return titulo;
    }
}

