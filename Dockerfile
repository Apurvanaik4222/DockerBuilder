FROM bellsoft/liberica-openjdk-alpine:17.0.8

# Install curl, jq, and bash (using apk instead of apt-get)
RUN apk update && apk add --no-cache curl jq bash



#Working directory
WORKDIR /home/selenium-docker

#Copying Files from dockerresources to docker container
ADD target/docker-resources   ./
ADD runner.sh                 runner.sh


#Start the Runner File
ENTRYPOINT sh runner.sh
