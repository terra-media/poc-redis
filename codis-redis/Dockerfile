FROM ubuntu

# dependencies
RUN apt-get -y update && apt-get install -y software-properties-common python-software-properties gcc libc6-dev make g++ curl build-essential tcl zookeeper zookeeperd git

RUN curl https://storage.googleapis.com/golang/go1.5.3.linux-amd64.tar.gz -o go1.5.3.linux-amd64.tar.gz \
	&& tar -C /usr/local -xzf go1.5.3.linux-amd64.tar.gz \
	&& rm go1.5.3.linux-amd64.tar.gz

RUN mkdir work \
	&& mkdir go

ENV PATH=$PATH:/usr/local/go/bin
ENV GOPATH=$HOME/work
ENV CODISPATH=$GOPATH/src/github.com/CodisLabs/codis
ENV PATH=$PATH:$GOPATH/bin:$CODISPATH/bin
ENV PATH=$PATH:$GOPATH/src/github.com/thiagohora/codis-ha

RUN go get -u -d github.com/CodisLabs/codis; exit 0

WORKDIR $CODISPATH

RUN go get -u -d github.com/thiagohora/codis-ha && make && make gotest

WORKDIR $GOPATH/src/github.com/thiagohora/codis-ha

RUN go build

WORKDIR $CODISPATH/bin

COPY codis.sh $CODISPATH/bin/start.sh
COPY config.ini $CODISPATH/bin/config.ini

EXPOSE 19000
EXPOSE 11000
EXPOSE 18087

RUN mkdir log

ENTRYPOINT ["start.sh"]

CMD ["start; exit 0"]
