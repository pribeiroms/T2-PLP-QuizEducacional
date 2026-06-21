# Proteção da branch `main`

A proteção deve estar ativa no GitHub antes da integração final. Como essa configuração é remota, confirme-a nas configurações do repositório antes da entrega.

## Configuração recomendada

1. Acesse **Settings** do repositório.
2. Abra **Branches** e selecione **Add branch protection rule**.
3. Em **Branch name pattern**, informe `main`.
4. Ative **Require a pull request before merging**.
5. Ative **Require approvals** e defina pelo menos uma aprovação, se houver mais integrantes na equipe.
6. Ative **Require conversation resolution before merging**.
7. Ative **Block force pushes** e mantenha a exclusão da branch bloqueada.
8. Salve em **Create** ou **Save changes**.

> A proteção é uma configuração remota do GitHub e não pode ser armazenada apenas nos arquivos do projeto.
