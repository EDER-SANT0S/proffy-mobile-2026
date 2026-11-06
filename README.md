# Proffy Mobile 2026

**Nome:** Éder Santos Franco
**Turma:** Sistemas de Informação – 6º período noturno

## Fluxo "Esqueci minha senha"

Criei a `ForgotPasswordActivity` seguindo o mesmo jeito das telas de cadastro: um `TextWatcher` no campo de e-mail que, a cada letra digitada, confere se o e-mail é válido e liga ou desliga o botão (cinza com "Entrar" quando está inválido, verde com "Enviar" quando está válido). Se o e-mail estiver preenchido mas errado, aparece a mensagem de erro no campo, e ela some quando o e-mail fica certo. A regra de validação ficou em um utilitário só (`ValidationUtils`), para não repetir código. Quando o usuário toca em "Enviar" com um e-mail válido, a tela abre a `FinallyActivity` por `Intent`, mandando o título, o texto e o nome do botão por extras. Lá, o botão "Voltar ao login" leva de volta para o login e limpa a pilha de telas.
