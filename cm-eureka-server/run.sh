#!/bin/bash
docker build -f Dockerfile -t cm-eureka-server .
docker run --name cm-eureka-server  -p8085:8085 --restart=always -d cm-eureka-server

