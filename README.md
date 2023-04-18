# E-Bike
## Descrição
### O projeto consiste em uma  api de uma aplicação para alugar bicicletas


## ⚙️ Tecnologias utilizadas:

- **Java** v17.0.0
- **Maven** v4.0
- **Spring Boot** v3.0.4
- **MySQL**
- **Postman** v10.6.7


## 🔌 Executando
É necessário possuir um usuário previamente cadastrado (de preferência com role Admin) para poder fazer o login. O login gera um token que dará acesso as rotas da aplicação
Níveis de acesso:
- **Admin** tem acesso as rotas de users e bikes
- **User** só tem acesso as rotas de location
```bash
# a senha criptografada abaixo corresponde a "123"

$ $2a$10$oVY17LMnx7czlNmPK9sV8.gQArVbEZAERB8JAHj6HmFbJEUI09z3G

# pode ser usada para criar um usuario ADMIN direto no banco de dados
```

## 📌 Endpoints
| Método | Endpoint |
| --- | --- |
| POST| v1/users/new 
| GET | v1//users
|GET|v1/users/(id)
|PUT|v1/users/(id)
|DELETE|v1/users/(id)
|POST|/login
|POST|v1/bikes/new
|GET|v1/bikes
|GET|v1/bikes/(id)
|PUT|v1/bikes/(id)
|DELETE|v1/bikes/(id)
|POST|v1/location
|GET|v1/locations

