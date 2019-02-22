#!/usr/bin/env bash

VERSION="0.0.1-SNAPSHOT"
mvn clean package -Dmaven.test.skip=true
docker build -t shopping-list:$VERSION -f ./docker/Dockerfile .
