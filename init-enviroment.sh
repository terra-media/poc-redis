#!bin/bash

# Dir with config file sentinel
DIR_CONF_SENTINEL=$PWD/docker-sentinel

## Start two container docker with redis in cluster
docker run --name redis-slave1 -d -v $DIR_CONF_SENTINEL:/etc/redis -p 6379:6379 -p 26379:26379 redis sh -c 'redis-server --appendonly yes --daemonize yes && redis-sentinel /etc/redis/sentinel1.conf' && docker run --name redis-slave2 -d -v $DIR_CONF_SENTINEL:/etc/redis -p 6380:6380 -p 26380:26380 redis sh -c 'redis-server --appendonly yes --slaveof 172.17.0.2 6379 --daemonize yes && redis-sentinel /etc/redis/sentinel2.conf' && docker run --name redis-slave3 -d -v $DIR_CONF_SENTINEL:/etc/redis -p 6381:6381 -p 26381:26381 redis sh -c 'redis-server --appendonly yes --slaveof 172.17.0.2 6379 --daemonize yes && redis-sentinel /etc/redis/sentinel3.conf'


echo '########################################################################'
echo '#                     INFORMATION REDIS/SENTINEL                       #'
echo '########################################################################'
echo '# Master| Redis/IP: 172.17.0.2:6379  Sentinel/IP: 172.17.0.2:26379     #'
echo '# Slave1| Redis/IP: 172.17.0.3:6379  Sentinel/IP: 172.17.0.3:26379     #'
echo '# Slave2| Redis/IP: 172.17.0.4:6379  Sentinel/IP: 172.17.0.4:26379     #'
echo '########################################################################'
