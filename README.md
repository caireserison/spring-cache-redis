# spring-cache-redis
Simple example of Spring's cache control using Redis | Exemplo simples do controle de cache do Spring utilizando Redis

PREPARAÇÃO DE AMBIENTE LOCAL

1- Instalação do Docker:
https://www.docker.com/get-started

2- Imagem do Redis no Docker:
Executar o comando “docker pull redis” no Docker

3- Executar última versão:
Executar o comando “docker run -d -p 6379:6379 redis:latest” no Docker

OU

Manual para instalação direta no Windows: <br />
https://medium.com/@programadriano/instalando-o-redis-em-um-servidor-windows-server-2016-bb2c3cb0a806

TESTES

Refletir nome: <br />
URI: http://localhost:8082/test-cache/name/Erison <br/>
METHOD: GET

Calcular idade: <br/>
URI: http://localhost:8082/test-cache <br/>
METHOD: POST <br/>
REQUEST: <br/>
```json
{
    "id": 1,
    "name": "Erison",
    "dateBirth": "1992-06-10"
}
```
