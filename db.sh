#!/usr/bin/env bash

# Pass Argument to tell to start clear

function dbNoDocker(){
    rm -rf db
    mkdir db
    mongod --dbpath db &
    sleep 1
    mongo ayitili --eval 'db.createUser({user: "admin", pwd: "password", roles: [{ role: "root", db: "admin" }]})'
    sleep 1
    pkill mongod
    mongod --auth --dbpath db
}

function dbDocker(){
    docker rm -f ayitili-mongo
    docker run -d --name ayitili-mongo -p 27017:27017 mongo --auth
    sleep 1
    docker exec ayitili-mongo mongo admin --eval 'db.createUser({user: "admin", pwd: "password", roles: [{ role: "root", db: "admin" }]})'
    docker exec ayitili-mongo mongo ayitili -u admin -p password --eval 'db.createUser({user: "admin", pwd: "password", roles: [{ role: "root", db: "admin" }]})' --authenticationDatabase admin
}

which docker

if [ $? -eq 0 ]
then
    docker --version | grep "Docker version"
    if [ $? -eq 0 ]
    then
        dbDocker
    else
        dbNodocker
    fi
else
    dbNodocker
fi
