# bank-spring
Api spring boot desenvolvida para atuar em um mini projeto de transações bancarias simples, utiliza jpa e banco de dados em memória h2.

## Passo a passo para rodar e utilizar
1. Clone esse repositório no seu ambiente local;
2. Atualize o maven para baixar as dependências e já pode rodar o projeto.
3. Acessar o h2 para visualizar as alterações em http://localhost:8080/h2-console/

## Utilização
- Rota para criar usuários metodo POST /users ( o formato deve ser json com os mesmo atributos do usuário dto ). 
- Rota para atualizar usuários metodo PUT /users ( o formato deve ser json com os mesmo atributos do usuário dto ).
- Rota para deletar usuários metodo DELETE /users?account={accountUser}.
- Rota para obeter todos os usuários metodo GET /users/all.
- Rota para obeter um os usuário metodo GET /users?account={accountUser}.

- Rota para criar uma transfência metodo POST /operations/transfer ( o formato deve ser json com os mesmo atributos do transfer dto ).
- Rota para criar um deposito metodo POST /operations/deposit ( o formato deve ser json com os mesmo atributos do operational dto ).
- Rota para criar um saque metodo POST /operations/withdraw ( o formato deve ser json com os mesmo atributos do operational dto ).
- Rota para obeter um o saldo do usuário metodo GET /operations/balance?account={accountUser}.
