#!/bin/sh

/etc/init.d/zookeeper start 

sleep 3

codis-config dashboard &> /dev/null &

sleep 4

codis-config slot init -f

sleep 2

codis-config server add 1 172.17.0.7:6379 master && codis-config server add 1 172.17.0.4:6379 slave && codis-config server add 2 172.17.0.5:6379 master && codis-config server add 2 172.17.0.2:6379 slave && codis-config server add 3 172.17.0.6:6379 master&& codis-config server add 3 172.17.0.3:6379 slave

sleep 2

codis-config slot set 1 1 offline && codis-config slot set 2 2 offline && codis-config slot set 3 3 offline

sleep 2

codis-config slot range-set 683 1023 1 online && codis-config slot range-set 342 682 2 online && codis-config slot range-set 0 341 3 online

sleep 4

codis-ha --codis-config=localhost:18087 --productName=codis-redis &> /dev/null &

sleep 2

codis-proxy -c config.ini -L ./log/proxy.log  --cpu=2 --addr=0.0.0.0:19000 --http-addr=0.0.0.0:11000



