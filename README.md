# Quiz Educacional

Projeto desenvolvido em Java para a criação de um quiz educacional. A aplicação permitirá apresentar perguntas, validar respostas e acompanhar a pontuação do participante.

## Requisitos

- JDK 17 ou superior
- Visual Studio Code (recomendado)
- Extension Pack for Java, da Microsoft

## Estrutura do projeto

```text
.
├── .vscode/             # Configurações recomendadas para o VS Code
├── docs/                # Documentação do projeto
├── src/
│   ├── main/java/       # Código-fonte da aplicação
│   └── test/java/       # Testes automatizados
├── .gitignore
└── README.md
```

## Como executar

No VS Code, abra `src/main/java/br/com/quiz/educacional/Main.java` e use o botão **Run** exibido acima do método `main`.

Também é possível executar pelo terminal, a partir da raiz do projeto:

```powershell
javac -encoding UTF-8 -d out src/main/java/br/com/quiz/educacional/Main.java
java -cp out br.com.quiz.educacional.Main
```

## Desenvolvimento

O projeto utiliza a branch `main` como branch estável. Novas funcionalidades devem ser desenvolvidas em branches próprias e integradas por pull request.

As instruções para configurar a proteção da `main` estão em [docs/PROTECAO_MAIN.md](docs/PROTECAO_MAIN.md).

## Status

Estrutura inicial configurada. O desenvolvimento das funcionalidades do quiz será realizado nas próximas etapas.

