FROM ubuntu

# add our user and group first to make sure their IDs get assigned consistently, regardless of whatever dependencies get added
RUN groupadd -r redis && useradd -r -g redis redis

RUN apt-get -y update && apt-get install -y software-properties-common python-software-properties

# for redis-sentinel see: http://redis.io/topics/sentinel
RUN add-apt-repository -y "deb http://archive.ubuntu.com/ubuntu precise universe" && \
    add-apt-repository -y "deb http://archive.ubuntu.com/ubuntu precise main restricted universe multiverse" && \
    add-apt-repository -y "deb http://archive.ubuntu.com/ubuntu precise-updates main restricted universe multiverse" && \
    add-apt-repository -y "deb http://archive.ubuntu.com/ubuntu precise-backports main restricted universe multiverse"
RUN apt-get update && apt-get -y upgrade

RUN apt-get install -y gcc libc6-dev make g++ curl build-essential libc6-dev tcl ruby --no-install-recommends \
	&& apt-get install -y rubygems \
	&& gem install redis \
	&& mkdir -p /usr/src/redis \
	&& mkdir -p /usr/src/redis/config \
	&& curl http://download.redis.io/releases/redis-3.0.6.tar.gz -o redis.tar.gz \
	&& echo "4b1c7b1201984bca8f7f9c6c58862f6928cf0a25 *redis.tar.gz" | sha1sum -c - \
	&& tar -xzf redis.tar.gz -C /usr/src/redis --strip-components=1 \
	&& rm redis.tar.gz \
	&& make -C /usr/src/redis \
	&& make -C /usr/src/redis install \
	&& ln -s /usr/src/redis/src/redis-trib.rb /usr/bin/redis-trib \
	&& apt-get purge -y --auto-remove gcc libc6-dev make curl python-software-properties g++ build-essential tcl libc6-dev

RUN mkdir /data && chown redis:redis /data
VOLUME /data
WORKDIR /data

EXPOSE 6379
CMD [ "redis-server" ]
CMD [ "redis-trib" ]
