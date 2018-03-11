#!/usr/bin/env bash

set -e
set -x

CUR_DATE=$(date "+%m-%d-%Y-%H.%M.%S")

sudo docker exec ayitili-mongo rm -rf /backup-data
sudo docker exec ayitili-mongo cp -r /data /backup-data
sudo docker commit --change "VOLUME /dummy" -m "Dev database at ${CUR_DATE}" ayitili-mongo nucklehead/ayitili-mongo:${CUR_DATE}
sudo docker tag nucklehead/ayitili-mongo:${CUR_DATE} nucklehead/ayitili-mongo:latest
sudo docker push nucklehead/ayitili-mongo:${CUR_DATE}
sudo docker push nucklehead/ayitili-mongo:latest