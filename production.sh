#!/bin/sh
# собирает новую версию гейта, которую затем необходимо поместить в контейнер.
if [ $1 = "build" ]; then
    sudo rm -r target
    sudo docker stop telegram-dictionary
    mvn install
fi
if [ $1 = "deploy" ]; then
  sudo docker rm telegram-dictionary
  sudo docker build -t olegthelilfix/telegram-dictionary .
  sudo docker run -d -p 8081:8081 --log-opt max-size=1200m --log-opt max-file=1 --name telegram-dictionary olegthelilfix/telegram-dictionary
fi
if [ $1 = "logs" ]; then
    sudo docker logs -f --tail 100 telegram-dictionary
fi

docker run --name postgres -p 5432:5432 -e POSTGRES_PASSWORD=fuckyou620695 -d postgres

docker run -d --name mongo-telegram -p 27017:27017 -e MONGO_INITDB_ROOT_USERNAME=admin -e MONGO_INITDB_ROOT_PASSWORD=fuckyou620695 -e MONGODB_APPLICATION_DATABASE=telegram -e MONGODB_APPLICATION_USER=user -e MONGODB_APPLICATION_PASS=fuckyou620695 mongo
