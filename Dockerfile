FROM bellsoft/liberica-openjdk-alpine:17.0.8

#Install curl jq
RUN apk add curl jq
RUN apt-get update && apt-get install -y bash


#Working directory
WORKDIR /home/selenium-docker

#Copying Files from dockerresources to docker container
ADD target/docker-resources   ./
ADD runner.sh                 runner.sh


#Start the Runner File
ENTRYPOINT sh runner.sh
