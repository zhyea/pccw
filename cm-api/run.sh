#!/bin/bash
docker build -f Dockerfile -t cm-api .
docker run --name cm-api -p8085:8085 --restart=always -d cm-api

