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
  sudo docker run -d -p 8081:8080 --log-opt max-size=1200m --log-opt max-file=1 --name telegram-dictionary olegthelilfix/telegram-dictionary
fi
if [ $1 = "logs" ]; then
    sudo docker logs -f --tail 100 telegram-dictionary
fi