# Transacoes-bancarias-e-login-de-clientes

Desenvolva um algoritmo que execute operações de autenticação e depois, de uma transferência bancária obedecendo os seguintes critérios:

A base de dados local deve conter usuários emissores e receptores de uma transferência, seguindo o modelo;
A senha de autenticação deve conter 8 dígitos, caracteres especiais, letras maiúsculas e minúsculas;
O usuário precisará estar autenticado para realizar uma transferência;
Toda transferência deverá ser feita entre um emissor e um receptor;
Operador e receptor iniciam a operação com um saldo de R$ 0,00;
As transferências deverão ser executadas de acordo com o seu tipo, sendo 3 os seus tipos: PIX, TED e DOC;
O limite de valor máximo permitido para uma transferência via PIX é de R$ 5 mil;
Transferências via TED só são permitidas para valores acima de R$ 5 mil e até R$ 10 mil;
Transferências via DOC só são permitidas para valores acima de R$ 10 mil;
Não serão permitidas transferências para a mesma conta, mas um emissor pode transferir para ele mesmo se for uma conta diferente;
As entradas deverão estar sempre com todos os dados preenchidos.
