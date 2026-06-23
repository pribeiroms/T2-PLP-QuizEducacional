# Quiz Educacional

Projeto desenvolvido em Java que apresenta perguntas, valida respostas, acompanha a pontuação do participante e gera um relatório de desempenho. O modo desafio limita cada resposta a 20 segundos usando programação concorrente.

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
$fontes = Get-ChildItem -Path src/main/java -Recurse -Filter *.java | ForEach-Object FullName
javac -encoding UTF-8 -d out $fontes
java -cp out br.com.quiz.educacional.Main

caso nao funcione rode:
cd /workspaces/T2-PLP-QuizEducacional && /usr/lib/jvm/java-17-openjdk-amd64/bin/java -version && echo "---" && rm -rf out && mkdir -p out && find src/main/java -name "*.java" | sort > /tmp/sources.txt && /usr/lib/jvm/java-17-openjdk-amd64/bin/javac -encoding UTF-8 -d out $(cat /tmp/sources.txt) && /usr/lib/jvm/java-17-openjdk-amd64/bin/java -cp out br.com.quiz.educacional.Main
```

## Funcionalidades

- Menu para iniciar o quiz, consultar o último resultado e sair
- Questões de múltipla escolha, verdadeiro ou falso e dissertativas
- Banco de questões e sistema de pontuação
- Correção automática com Streams e expressões lambda
- Relatório com acertos, erros e percentual de aproveitamento
- Modo desafio com temporizador de 20 segundos por questão

## Testes

Os testes automatizados não exigem bibliotecas externas. Para executá-los:

```powershell
$fontes = Get-ChildItem -Path src/main/java,src/test/java -Recurse -Filter *.java | ForEach-Object FullName
javac -encoding UTF-8 -d out $fontes
java -cp out br.com.quiz.educacional.TestesAutomatizados
```

## Desenvolvimento

O projeto utiliza a branch `main` como branch estável. Novas funcionalidades devem ser desenvolvidas em branches próprias e integradas por pull request.

As instruções para configurar a proteção da `main` estão em [docs/PROTECAO_MAIN.md](docs/PROTECAO_MAIN.md).

## Status

Funcionalidades principais implementadas e reunidas na branch de integração. A integração final com a `main` deve ser feita por pull request.
