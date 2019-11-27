#!/bin/sh
echo 'Ingrese un puerto disponible'
read PORT

JAVA_DIR='/usr/java/jdk1.8.0_171-amd64/bin/'

$JAVA_DIR/java -jar target/manager-0.0.1-SNAPSHOT.jar --server.port=$PORT &
echo 'Espere :v'
echo 'Por favor no se olvide de matar el proceso en este puerto, con kill -9 $(lsof -t -i:'$PORT')'
sleep 5s
echo 'Ingrese a: ' http://localhost:$PORT/process