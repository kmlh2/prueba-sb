# Spring boot - jpa - webflux
Para correr localmente, se necesita la base de datos.
```
docker-compose up -d
```

* El -d, significa __detached__

## Configurar la base de datos

Acceder a url http://localhost:8081/ 
```
Usuario: root
Clave: example
```

* Si es la primera vez que ejecuta, debe crear la base de datos con el comando de creacion:
```
CREATE DATABASE store;
```


## Ejecutar la aplicacion

* Para ejecutar la applicacion e necesario ejecutar el siguiente comando
```
./gradlew bootRun
```


## Llenar la base de datos con información de pruebas

Llamara:
```
http://localhost:3000/api/seed
```