#!/bin/bash
MYSQL_DATABASES_PATH=/home/simpletask/Work/docker/volumes/microservice

docker compose down

sudo rm -rf $MYSQL_DATABASES_PATH

mkdir $MYSQL_DATABASES_PATH

for service in $(ls -d */ | grep -o ".*Service"); do
  service_name_lowercase="${service,,}"
  docker image rm -f $service_name_lowercase
  docker build -t $service_name_lowercase ./$service
done

docker compose up -d