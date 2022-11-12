# Evaluacion Falabella
Para correr localmente, se necesita la base de datos. Por lo cual es necesario ejecutar el siguiente comando en la raiz del proyecto

```
docker-compose up -d
```

* El -d, significa __detached__

## Configurar la base de datos

Si es la primera vez que ejecuta, debe crear la base de datos con los siguientes pasos

* Acceder a url http://localhost:8081/ 
```
Usuario: root
Clave: example
```

* Ejecute comando de creacion base de datos:
```
CREATE DATABASE store;
```
![img_1.png](img/img_1.png)

## Construir y ejecutar la aplicacion

* Para construir y ejecutar la applicacion es necesario ingresar a la carpeta del proyecto y ejecutar los siguientes comandos

Linux - OSX:

* Construccion
```
./gradlew clean build
```
* Ejecucion
```
cd build/libs/
java -jar prueba-0.0.1-SNAPSHOT.jar
```

Window:
* Construccion
```
gradlew.bat clean build
```
* Ejecucion
```
cd build/libs/
java -jar prueba-0.0.1-SNAPSHOT.jar
```


## Llenar la base de datos con información de pruebas

Llamara:
```
http://localhost:3000/api/seed
```