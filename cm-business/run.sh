#!/bin/bash
docker build -f Dockerfile -t cm-business .
docker run --name cm-business -p8087:8087 --restart=always -d cm-business
