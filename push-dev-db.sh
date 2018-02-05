#!/usr/bin/env bash

sudo docker commit -m "Dev database at $(date "+%m-%d-%Y")" ayitili-mongo nucklehead/ayitili-mongo:$(date "+%m-%d-%Y")
sudo docker push nucklehead/ayitili-mongo