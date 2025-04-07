## Histórias de Usuário

### 1. Cadastro de Usuário  
**Título:** Usuário se cadastra no sistema  
**Como** um usuário,  
**Eu quero** me cadastrar no sistema,  
**Para que** eu possa acessar suas funcionalidades.  

**Critérios de Aceitação:**  
- O usuário deve fornecer nome, email e senha.  
- O sistema deve validar o email antes de concluir o cadastro.  
- O usuário deve receber uma confirmação de cadastro por email.

---

### 2. Login no Sistema  
**Título:** Usuário realiza login  
**Como** um usuário cadastrado,  
**Eu quero** fazer login no sistema,  
**Para que** eu possa acessar minhas funcionalidades personalizadas.  

**Critérios de Aceitação:**  
- O usuário deve fornecer email e senha válidos.  
- O sistema deve autenticar os dados e permitir o acesso.  
- Caso os dados sejam inválidos, o sistema deve exibir uma mensagem de erro.  

---

### 3. Criar Pedido de Aluguel  
**Título:** Cliente cria um pedido de aluguel  
**Como** um cliente,  
**Eu quero** criar um pedido de aluguel de automóvel,  
**Para que** eu possa solicitar um carro para locação.  

**Critérios de Aceitação:**  
- O cliente deve fornecer informações sobre o automóvel desejado.  
- O pedido deve ser registrado com o status "Pendente".  
- O cliente deve receber uma confirmação do pedido.  

---

### 4. Modificar Pedido de Aluguel  
**Título:** Cliente modifica um pedido de aluguel  
**Como** um cliente,  
**Eu quero** modificar um pedido de aluguel,  
**Para que** eu possa atualizar as informações antes da aprovação.  

**Critérios de Aceitação:**  
- O cliente só pode modificar pedidos com status "Pendente".  
- O sistema deve permitir alteração dos detalhes do pedido.  
- O cliente deve receber uma confirmação das alterações.  

---

### 5. Cancelar Pedido de Aluguel  
**Título:** Cliente cancela um pedido de aluguel  
**Como** um cliente,  
**Eu quero** cancelar um pedido de aluguel,  
**Para que** eu possa desistir da solicitação antes da aprovação.  

**Critérios de Aceitação:**  
- O cliente só pode cancelar pedidos com status "Pendente".  
- O sistema deve registrar o status como "Cancelado".  
- O cliente deve receber uma confirmação do cancelamento.  

---

### 6. Consultar Pedido de Aluguel  
**Título:** Cliente consulta o status do pedido  
**Como** um cliente,  
**Eu quero** consultar meus pedidos de aluguel,  
**Para que** eu possa acompanhar seu status.  

**Critérios de Aceitação:**  
- O sistema deve exibir todos os pedidos do cliente.  
- Deve ser possível visualizar detalhes de cada pedido.  
- O cliente pode ver o status do pedido (Pendente, Aprovado, Cancelado).  

---

### 7. Avaliar Pedido de Aluguel  
**Título:** Agente avalia um pedido  
**Como** um agente,  
**Eu quero** avaliar um pedido de aluguel,  
**Para que** eu possa aprová-lo ou rejeitá-lo.  

**Critérios de Aceitação:**  
- O agente pode visualizar os detalhes do pedido e os dados financeiros do cliente.  
- O agente pode aprovar ou rejeitar o pedido.  
- O sistema deve registrar a decisão e notificar o cliente.  

---

### 8. Gerar Contrato de Aluguel  
**Título:** Agente gera um contrato de aluguel  
**Como** um agente,  
**Eu quero** gerar um contrato de aluguel,  
**Para que** o cliente possa formalizar a locação do automóvel.  

**Critérios de Aceitação:**  
- O contrato só pode ser gerado para pedidos aprovados.  
- O contrato deve incluir detalhes do automóvel, cliente e tipo de contrato.  
- O sistema deve armazenar o contrato e permitir sua consulta posterior.
