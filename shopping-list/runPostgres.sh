#!/usr/bin/env bash

docker run -e POSTGRES_PASSWORD=mysecretpassword -p 5432:5432 --name postgres -d postgres