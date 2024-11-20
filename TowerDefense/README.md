# Tower Defense Game

## Clases

```bash
towerdefense
   ├── Enemy.java
   ├── Map.java
   ├── Player.java
   ├── Tower.java
   ├── TowerDefense.java
   |── TowerDefenseGame.java       
   └── Wave.java
```

## Construir imagen docker

`docker build -t tower-game .`

### Resultado

```docker
[+] Building 1.1s (11/11) FINISHED                        docker:desktop-linux
 => [internal] load build definition from Dockerfile                      0.0s
 => => transferring dockerfile: 242B                                      0.0s 
 => [internal] load metadata for docker.io/library/openjdk:17             0.9s 
 => [auth] library/openjdk:pull token for registry-1.docker.io            0.0s 
 => [internal] load .dockerignore                                         0.0s
 => => transferring context: 2B                                           0.0s 
 => [1/5] FROM docker.io/library/openjdk:17@sha256:528707081fdb9562eb819  0.0s 
 => [internal] load build context                                         0.0s 
 => => transferring context: 739B                                         0.0s 
 => CACHED [2/5] WORKDIR /app                                             0.0s 
 => CACHED [3/5] COPY ./TowerDefense/src/main/java/ /app/src              0.0s 
 => CACHED [4/5] RUN javac src/cc3s2/towerdefense/*.java -d out           0.0s 
 => CACHED [5/5] WORKDIR /app/out                                         0.0s 
 => exporting to image                                                    0.0s 
 => => exporting layers                                                   0.0s 
 => => writing image sha256:ecad4ffb1c0c8e40abaab4231edcd2f398327aeeed6f  0.0s 
 => => naming to docker.io/library/tower-game                             0.0s 
```

## Ejecución del contenedor

`docker run -it --name tower-container tower-game`

### Resultado

```bash
$ docker run -it --name tower-container tower-game
Juego Iniciado - Bienvenido
Elija un tamaño de tablero valido
10
1. Place Tower
2. Start Wave
3. Show Game State
4. Exit
Choose an option: 3
[ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
[ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
[ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
[ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
[ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
[ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
[ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
[ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
[ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
[ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]

Puntuacion: 0
Vida de la Base: 100
1. Place Tower
2. Start Wave
3. Show Game State
4. Exit
Choose an option: 4
```

## Configuracion de redes y volumenes en Docker

```bash
$ docker network create game-network
cd8eb4be9ac4a47986a8c604b367f66e2e9c54d7a20cfbb3c744c7346dc01ad6
```

`docker run -it --name game-container --network game-network tower-game`

### Resultado

```bash

$ docker run -it --name game-container --network game-network tower-game
Juego Iniciado - Bienvenido
Elija un tamaño de tablero valido
5
1. Place Tower
2. Start Wave
3. Show Game State
4. Exit
Choose an option: 3
[ ][ ][ ][ ][ ]
[ ][ ][ ][ ][ ]
[ ][ ][ ][ ][ ]
[ ][ ][ ][ ][ ]
[ ][ ][ ][ ][ ]

Puntuacion: 0
Vida de la Base: 100
1. Place Tower
2. Start Wave
3. Show Game State
4. Exit
Choose an option: 4
```

Contenedor conectado a la red
Inspeccionar con comando: `docker network inspect game-network`

```bash
$ docker network inspect game-network
[
    {
        "Name": "game-network",
        "Id": "cd8eb4be9ac4a47986a8c604b367f66e2e9c54d7a20cfbb3c744c7346dc01ad6",
        "Created": "2024-11-19T22:20:06.006338055Z",
        "Scope": "local",
        "Driver": "bridge",
        "EnableIPv6": false,
        "IPAM": {
            "Driver": "default",
            "Options": {},
            "Config": [
                {
                    "Subnet": "172.20.0.0/16",
                    "Gateway": "172.20.0.1"
                }
            ]
        },
        "Internal": false,
        "Attachable": false,
        "Ingress": false,
        "ConfigFrom": {
            "Network": ""
        },
        "ConfigOnly": false,
        "Containers": {
            "bd5bfe7edddd65d41b78caaf30d9000fedd8b620f29132c775e717a3ca95f090": {
                "Name": "game-container",
                "EndpointID": "a6db0eb611fe3a2eea7c9512fb69f2f3b180bfa0eba62328d161eba87ec018ff",
                "MacAddress": "02:42:ac:14:00:02",
                "IPv4Address": "172.20.0.2/16",
                "IPv6Address": ""
            }
        },
        "Options": {},
        "Labels": {}
    }
]
```

## Crear y montar un volumen

`docker volume create game-data`

```bash
$ docker volume create game-data
game-data
```

`docker run -it --name game-container --network game-network -v game-data:/app/data tower-game`



## Conceptos

``Docker`` : plataforma que permite a los desarrolladores automatizar la implementación deaplicaciones como contenedores portátiles, autónomos y ligeros.

``Contenedor Docker``: Puede ejecutar una aplicación en cualquier entorno

`Redes en Docker`: Capacidad de generar redes aisladas para que los contenedores se comuniquen entre sí

comando: `docker network create <network-name>`

Unir un contenedor a la red con: `--network  <network-name>`


`Volumenes en Docker`: Se utilizan para persistir datos generados y utilizados por los contenedores. Pueden ser compartidos entre contenedores

crear: `docker volume create <volume-name>`
montarlo al contenedor añadiendo: `-v <volume-name>:/app/data`
