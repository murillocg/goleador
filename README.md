[![Build Status](https://travis-ci.org/murillocg/goleador.svg?branch=master)](https://travis-ci.org/murillocg/goleador)
[![codecov](https://codecov.io/gh/murillocg/goleador/branch/master/graph/badge.svg)](https://codecov.io/gh/murillocg/goleador)

# GoleadorApp

API REST para gerenciamento de partidas de futebol amador, contemplando informações como placar das partidas, gols anotados por cada jogador e ranking dos goleadores.

## Tecnologias

- [Java](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
- [Maven](https://maven.apache.org/)
- [Spring Boot](http://projects.spring.io/spring-boot/)
- [Liquibase](http://www.liquibase.org/)
- [Travis CI](https://travis-ci.org/)
- [Docker](http://docker.com/)
- [Angular 4](https://angular.io/)

## Configurando para desenvolvimento

Para que seja possível compilar o projeto, você deve instalar as seguintes dependências:

1. [Node.js][]: Utilizado para rodar um servidor web em modo desenvolvimento e compilar o projeto.
2. [Yarn][]: Utilizado para gerenciar as dependências do Node.

Após este passo, você poderá rodar o seguinte comando para instalar as ferramentas de desenvolvimento.
Você somente precisa rodar este comando quando alterar as dependências no [package.json](package.json).

    yarn install

Nós utilizamos scripts do yarn e [Webpack][] para compilação do front-end.

Rode os seguintes comandos em dois terminais separados para criar um ótimo ambiente de desenvolvimento 
onde o browser atualiza automaticamente sempre que os arquivos são alterados e salvos.

    mvn
    yarn start
    
## Compilando para produção

Para otimizar a aplicação para produção, rode:

    mvn -Pprod clean package

Neste perfil, os arquivos CSS e JavaScript serão concatenados e minificados. 
Também será modificado o arquivo `index.html` para referenciar estes novos arquivos.
Para garantir que tudo está funcionando, execute:

    java -jar target/*.war

Então acesse o endereço [http://localhost:8080](http://localhost:8080) no browser.

## Testando

Para rodar os testes da aplicação, execute:

    mvn clean test

## Usando docker para rodar

    $ docker run -it -p 8080:8080 --name goleadorApp murillocg/goleador

## Licença

MIT

[Node.js]: https://nodejs.org/
[Yarn]: https://yarnpkg.org/
[Webpack]: https://webpack.github.io/