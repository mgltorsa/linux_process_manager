#!/bin/sh
echo 'Ingrese un puerto disponible'
read PORT

JAVA_DIR='/usr/java/jdk1.8.0_171-amd64/bin/'

$JAVA_DIR/java -jar target/manager-0.0.1-SNAPSHOT.jar --server.port=$PORT &
echo 'Espere :v'
sleep 30s
firefox http://localhost:$PORT/process