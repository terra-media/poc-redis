# Dir with config file sentinel
DIR_CONF_SENTINEL=$PWD/config

## Start two container docker with redis in cluster
docker stop redis-slave1 
docker stop redis-slave2 
docker stop redis-slave3
echo '##############################################################################'
echo '#                     INFORMATION REDIS/SENTINEL                             #'
echo '##############################################################################'
echo '# Stopping| Redis/IP: 172.17.0.2:6379  Sentinel/IP: 172.17.0.2:26379 (master)#'
echo '# Stopping| Redis/IP: 172.17.0.3:6379  Sentinel/IP: 172.17.0.3:26379         #'
echo '# Stopping| Redis/IP: 172.17.0.4:6379  Sentinel/IP: 172.17.0.4:26379         #'
echo '##############################################################################'
docker rm redis-slave1 
docker rm redis-slave2 
docker rm redis-slave3
echo '##############################################################################'
echo '#                                STOP ALL                                    #'
echo '##############################################################################'

