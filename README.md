# poc-redis-sentinel + docker

- Configurar com docker o redis/sentinel em cluster.
- Acessar slave/master
- App com integração com spring-data-redis
- Tirar e colocar nós


<h6>Inicializando os ambientes com o docker </h6>
```
  $ git clone https://github.com/terra-media/poc-redis.git
  $ cd poc-redis
  $ sh init-enviroment.sh
```  

<h6>Acessando os containers </h6>
```
############################################################################
#                     INFORMATION REDIS/SENTINEL                           #
############################################################################
# Slave1| Redis/IP: 172.17.0.2:6379  Sentinel/IP: 172.17.0.2:26379 (master)#
# Slave2| Redis/IP: 172.17.0.3:6379  Sentinel/IP: 172.17.0.3:26379         #
# Slave3| Redis/IP: 172.17.0.4:6379  Sentinel/IP: 172.17.0.4:26379         #
############################################################################


$ docker ps
$ docker exec -i -t redis-slave1 bash
$ docker exec -i -t redis-slave2 bash
$ docker exec -i -t redis-slave3 bash

```  

<h6>Acessando o Redis </h6>
OBS: Execute os comandos abaixo só após ter conectado em um dos containers(redis-slave1, redis-slave2 ou redis-slave3)
```
## Para acessar o redis pelo redis-cli
$ redis-cli -p 6379

## Mostrar informações quem é o master, slave etc.
$ INFO REPLICATION

## Para sair
$ exit

``` 

<h6>Acessando o Sentinel </h6>
OBS: Execute os comandos abaixo só após ter conectado em um dos containers(redis-slave1, redis-slave2 ou redis-slave3)
```
## Para acessar o Sentinel
$ redis-cli -p 26379

## Mostrar informações quantidade de slave, master, instancias sentinel up e etc.
$ INFO SENTINEL

## Para sair
$ exit

``` 

<h6>Subindo a aplicação integrado com redis</h6>
```
cd SpringBootRedisSentinel/ && mvn spring-boot:run

``` 

<h6>Parando um instancia master/slave </h6>
```
## Exibir instancias UP
docker ps

## Para acessar o redis pelo redis-cli
docker stop <NAME_CONTAINER>

``` 
