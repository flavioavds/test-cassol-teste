### Desafio Técnico para Desenvolvedor Java Spring Boot

 

#### Objetivo

Desenvolver uma API RESTful utilizando Java Spring Boot que permita gerenciar cursos e calcular a nota média dos alunos em um determinado curso. A aplicação deverá utilizar o banco de dados em memória H2.

 

#### Realização do Projeto

 

1. **Configuração do Projeto:**

   - Utilizando o Spring Boot para criar a aplicação.
     Foi realizado o Spring Boot para criar a aplicação com projeto Maven, Java 17, Spring 3.3.1, com dependencias spring web, jpa, devtools, lombok,
     flywaydb para popular banco de dados h2, validation e swagger.

   - A Configuração do banco de dados H2 para armazenamento dos dados. Com JDBC URL: jdbc:h2:mem:test-curso,
     User Name:sa e Password: (em branco).

 

2. **Foram Criadas as Entidades**

   - **Curso:**

     - `id` (Long): Identificador único do curso.

     - `nome` (String): Nome do curso.

     - `descricao` (String): Descrição do curso.

   - **Aluno:**

     - `id` (Long): Identificador único do aluno.

     - `nome` (String): Nome do aluno.

   - **Matricula:**

     - `id` (Long): Identificador único da matrícula.

     - `aluno` (Aluno): Referência ao aluno matriculado.

     - `curso` (Curso): Referência ao curso.

     - `nota` (Double): Nota do aluno no curso.

 

3. **Endpoints da API:**

   - **Curso:**

     - `POST /cursos`: Criar um novo curso.

     - `GET /cursos`: Listar todos os cursos.

     - `GET /cursos/{id}`: Obter detalhes de um curso específico.

     - `PUT /cursos/{id}`: Atualizar um curso existente.

     - `DELETE /cursos/{id}`: Deletar um curso.

   - **Aluno:**

     - `POST /alunos`: Criar um novo aluno.

     - `GET /alunos`: Listar todos os alunos.

     - `GET /alunos/{id}`: Obter detalhes de um aluno específico.

     - `PUT /alunos/{id}`: Atualizar um aluno existente.

     - `DELETE /alunos/{id}`: Deletar um aluno.

     - `GET /alunos/{id}/cursos`: Obter a relação de Cursos que o aluno está matriculado.

 

   - **Matrícula:**

     - `POST /matriculas`: Matricular um aluno em um curso.

     - `GET /matriculas`: Listar todas as matrículas.

     - `GET /matriculas/{id}`: Obter detalhes de uma matrícula específica.

     - `PUT /matriculas/{id}`: Atualizar a nota de uma matrícula.

     - `DELETE /matriculas/{id}`: Deletar uma matrícula.

   - **Nota Média:**

     - `GET /cursos/{id}/media`: Obter a nota média dos alunos em um determinado curso.

 

4. **Sobre as Regras de Negócio:**

   - Um aluno pode estar matriculado em vários cursos.

   - Um curso pode ter vários alunos matriculados.

   - A nota média de um curso deve ser calculada como a média das notas de todos os alunos matriculados naquele curso.

 

5. **Com Validação e Tratamento de Erros:**

   - Implemente validações adequadas para garantir a integridade dos dados.

   - Garanta que respostas apropriadas sejam retornadas em caso de erros, como 404 para recursos não encontrados ou 400 para dados inválidos.

 

#### Para acesso ao Swagger

 

1. [Código fonte do projeto, incluindo a configuração do banco de dados H2.](http://localhost:8080/h2-console))

2. [Documentação da API (pode ser gerada utilizando Swagger ou outra ferramenta similar).](http://localhost:8080/swagger-ui/index.html#/)

    

#### Projeto com Critérios de:

 

1. **Funcionalidade:**

   - A API atende a todos os requisitos funcionais especificados.

2. **Qualidade do Código:**

   - Estrutura e organização do código.

   - Uso apropriado de princípios de orientação a objetos.

3. **Boas Práticas:**

   - Uso de boas práticas de desenvolvimento, incluindo tratamento de erros e validações.

 
