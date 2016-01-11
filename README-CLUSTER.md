# poc-redis-cluster + docker

- Configurar em Cluster o Redis em 3 containers, sendo 3 masters e 3 slaves.
- Acessar slave/master
- App com integração com spring-data-redis
- Tirar e colocar nós

<h6>Pré-requisitos </h6>
- Docker


<h6>Inicializando os ambientes com o docker </h6>
```
  $ git clone https://github.com/terra-media/poc-redis.git
  $ cd poc-redis/redis-cluster
  ## execute ./start.sh xxx xxx xxx xxx xxx xxx onde xxx deverá ser o IP de configuração de cada container. 
  $ ./start.sh 172.17.0.2 172.17.0.3 172.17.0.4 172.17.0.5 172.17.0.6 172.17.0.7
```  


<h6>Acessando o Redis </h6>
```
## Para acessar o redis pelo redis-cli. Caso não tenha instalado(sudo apt-get install redis-tools)
$ redis-cli -c -h <IP_CONTAINER> -p 6379

## Mostrar informações quem é o master, slave etc.
$ INFO REPLICATION

## Para sair
$ exit


<h6>Parando um instancia master/slave </h6>
```
## Exibir instancias UP
docker ps

## Para acessar o redis pelo redis-cli
docker stop <NAME_CONTAINER>

``` 

## Caso queira parar todos os containers dockers
$ ./stop.sh
