#!/bin/bash

CURRENT_CONFIG_DIR=$PWD 
CONTAINER_CONFIG_DIR=/usr/src/redis/config
DEFAULT_PORT=6379

echo $CURRENT_CONFIG_DIR
echo $CONTAINER_CONFIG_DIR
echo $DEFAULT_PORT

echo  docker run -d --name master1-redis -v $CURRENT_CONFIG_DIR/config:$CONTAINER_CONFIG_DIR redis-cluster redis-server $CONTAINER_CONFIG_DIR/redis-master1.conf
docker run -d --name master1-redis -v $CURRENT_CONFIG_DIR/config:$CONTAINER_CONFIG_DIR redis-cluster redis-server $CONTAINER_CONFIG_DIR/redis-master1.conf

echo  docker run -d --name master2-redis -v $CURRENT_CONFIG_DIR/config/:$CONTAINER_CONFIG_DIR redis-cluster redis-server $CONTAINER_CONFIG_DIR/redis-master2.conf
docker run -d --name master2-redis -v $CURRENT_CONFIG_DIR/config/:$CONTAINER_CONFIG_DIR redis-cluster redis-server $CONTAINER_CONFIG_DIR/redis-master2.conf

echo  docker run -d --name master3-redis -v $CURRENT_CONFIG_DIR/config/:$CONTAINER_CONFIG_DIR redis-cluster redis-server $CONTAINER_CONFIG_DIR/redis-master3.conf
docker run -d --name master3-redis -v $CURRENT_CONFIG_DIR/config/:$CONTAINER_CONFIG_DIR redis-cluster redis-server $CONTAINER_CONFIG_DIR/redis-master3.conf

echo  docker run -d --name slave1-redis -v $CURRENT_CONFIG_DIR/config/:$CONTAINER_CONFIG_DIR redis-cluster redis-server $CONTAINER_CONFIG_DIR/redis-slave1.conf
docker run -d --name slave1-redis -v $CURRENT_CONFIG_DIR/config/:$CONTAINER_CONFIG_DIR redis-cluster redis-server $CONTAINER_CONFIG_DIR/redis-slave1.conf

echo  docker run -d --name slave2-redis -v $CURRENT_CONFIG_DIR/config/:$CONTAINER_CONFIG_DIR redis-cluster redis-server $CONTAINER_CONFIG_DIR/redis-slave2.conf
docker run -d --name slave2-redis -v $CURRENT_CONFIG_DIR/config/:$CONTAINER_CONFIG_DIR redis-cluster redis-server $CONTAINER_CONFIG_DIR/redis-slave2.conf

echo  docker run -d --name slave3-redis -v $CURRENT_CONFIG_DIR/config/:$CONTAINER_CONFIG_DIR redis-cluster redis-server $CONTAINER_CONFIG_DIR/redis-slave3.conf
docker run -d --name slave3-redis -v $CURRENT_CONFIG_DIR/config/:$CONTAINER_CONFIG_DIR redis-cluster redis-server $CONTAINER_CONFIG_DIR/redis-slave3.conf

#echo  docker exec -it master1-redis redis-trib create --replicas 1 $1:$DEFAULT_PORT $2:$DEFAULT_PORT $3:$DEFAULT_PORT $4:$DEFAULT_PORT $5:$DEFAULT_PORT $6:$DEFAULT_PORT
#docker exec -it master1-redis redis-trib create --replicas 1 $1:$DEFAULT_PORT $2:$DEFAULT_PORT $3:$DEFAULT_PORT $4:$DEFAULT_PORT $5:$DEFAULT_PORT $6:$DEFAULT_PORT
