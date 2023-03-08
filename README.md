# Transacoes-bancarias-e-login-de-clientes

Desenvolva um algoritmo que execute operações de autenticação e depois, de uma transferência bancária obedecendo os seguintes critérios:

1. A base de dados local deve conter usuários emissores e receptores de uma transferência, seguindo o modelo;
2. A senha de autenticação deve conter 8 dígitos, caracteres especiais, letras maiúsculas e minúsculas;
3. O usuário precisará estar autenticado para realizar uma transferência;
4. Toda transferência deverá ser feita entre um emissor e um receptor;
5. As transferências deverão ser executadas de acordo com o seu tipo, sendo 3 os seus tipos: PIX, TED e DOC;
6. O limite de valor máximo permitido para uma transferência via PIX é de R$ 5 mil;
7. Transferências via TED só são permitidas para valores acima de R$ 5 mil e até R$ 10 mil;
8. Transferências via DOC só são permitidas para valores acima de R$ 10 mil;
9. Não serão permitidas transferências para a mesma conta, mas um emissor pode transferir para ele mesmo se for uma conta diferente;
10. As entradas deverão estar sempre com todos os dados preenchidos.
