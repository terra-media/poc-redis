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
ENV CODISPATH=$GOPATH/src/github.com/wandoulabs/codis/bin
ENV PATH=$PATH:$GOPATH/bin:$CODISPATH
ENV PATH=$PATH:$GOPATH/src/github.com/ngaut/codis-ha

RUN go get -u -d github.com/wandoulabs/codis ; exit 0

WORKDIR $GOPATH/src/github.com/wandoulabs/codis

RUN go get -u -d github.com/ngaut/codis-ha && make && make gotest

WORKDIR $GOPATH/src/github.com/ngaut/codis-ha

RUN go build

COPY codis.sh $CODISPATH/start.sh
COPY config.ini $CODISPATH/config.ini

EXPOSE 19000
EXPOSE 11000
EXPOSE 18087

WORKDIR $CODISPATH

RUN mkdir log

ENTRYPOINT ["start.sh"]

CMD ["start; exit 0"]
