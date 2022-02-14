# Avaliação IV Compass

Api rest que possui a estrutura para gerenciar partidos políticos e pessoas associadas a eles.

## Antes de tudo:
Iniciar o container docker que contém o MongoDB, verificar disponibilidade das portas <b>27017(mongo) e 8081(mongo-express)</b>

Comando pra subir o container:
```bash
docker-compose -f docker-compose.yaml up
```

Link pra olhar os dados do mongo: [Mongo Express](http://localhost:8081/)

## Documentação Swagger dos micro-serviços:

[Associado Service](http://localhost:8090/swagger-ui/index.html#/)

[Partido Service](http://localhost:8080/swagger-ui/index.html#/)
