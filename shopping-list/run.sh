#!/usr/bin/env bash

VERSION="0.0.1-SNAPSHOT"
docker run -p 8080:8080 shopping-list:$VERSION
