# Actividad Play with Docker 
# Stage 1

## Clone Lab's GitHub Repo 

`git clone https://github.com/dockersamples/linux_tweet_app`

## Correr contenedores Docker simples

### Contenedor Alpine Linux

`docker container run alpine hostname` 

```bash
[node1] (local) root@192.168.0.18 /
$ docker container run alpine hostname
Unable to find image 'alpine:latest' locallylatest: Pulling from library/alpine
da9db072f522: Pull complete
Digest: sha256:1e42bbe2508154c9126d48c2b8a75420c3544343bf86fd041fb7527e017a4b
4a
Status: Downloaded newer image for alpine:latest
5de05e87d4ed
```

la imagen `alpine:latest` no se encuentra de manera local. En estos casos Docker la obtiene de Docker Hub. Se muestra el nombre del host del contenedor (5de05e87d4ed)

Docker mantiene un contenedor en ejecución mientras el proceso dentro siga en ejecución. No se elimina el contenedor. Aún existe en el estado  `Exited`

` docker container ls --all`

```bash
[node1] (local) root@192.168.0.18 ~
$  docker container ls --all
CONTAINER ID   IMAGE     COMMAND      CREATED          STATUS
      PORTS     NAMES
5de05e87d4ed   alpine    "hostname"   22 minutes ago   Exited (0) 22 minutes
ago             intelligent_dewdney
```

### Contenedor interactivo Ubuntu

`docker container run --interactive --tty --rm ubuntu bash`

```bash
[node1] (local) root@192.168.0.18 ~
$ docker container run --interactive --tty --rm ubuntu bash
Unable to find image 'ubuntu:latest' locally
latest: Pulling from library/ubuntu
afad30e59d72: Pull complete
Digest: sha256:278628f08d4979fb9af9ead44277dbc9c92c2465922310916ad0c46ec9999295
Status: Downloaded newer image for ubuntu:latest
```

### MySQL container

```bash
docker container run \
 --detach \
 --name mydb \
 -e MYSQL_ROOT_PASSWORD=my-secret-pw \
 mysql:latest
```

```bash
[node1] (local) root@192.168.0.18 ~
$ docker container run \
> --detach \
> --name mydb \
> -e MYSQL_ROOT_PASSWORD=my-secret-pd \
> mysql:latest
Unable to find image 'mysql:latest' locally
latest: Pulling from library/mysql
2c0a233485c3: Pull complete
cb5a6a8519b2: Pull complete
570d30cf82c5: Pull complete
a841bff36f3c: Pull complete
80ba30c57782: Pull complete
5e49e1f26961: Pull complete
ced670fc7f1c: Pull complete
0b9dc7ad7f03: Pull complete
cd0d5df9937b: Pull complete
1f87d67b89c6: Pull complete
Digest: sha256:0255b469f0135a0236d672d60e3154ae2f4538b146744966d96440318cc822
c6
Status: Downloaded newer image for mysql:latest8059e86552a75d500451c6180a183345ac4d4118b6adc22df8f21d11edbd32de
```

```bash
[node1] (local) root@192.168.0.18 ~
$ docker container ls
CONTAINER ID   IMAGE          COMMAND                  CREATED         STATUS         PORTS                 NAMES
8059e86552a7   mysql:latest   "docker-entrypoint.s…"   9 minutes ago   Up 9 minutes   3306/tcp, 33060/tcp   mydb
```

```bash
[node1] (local) root@192.168.0.18 ~
$ docker container top mydb
PID                 USER                TIME                COMMAND
4027                999                 0:05                mysqld
```

```bash
[node1] (local) root@192.168.0.18 ~
$  docker exec -it mydb \
>  mysql --user=root --password=$MYSQL_ROOT_PASSWORD --version
 docker exec -it mydb \
 mysql --user=root --password=$MYSQL_ROOT_PASSWORD --version
mysql: [Warning] Using a password on the command line interface can beinsecure.
mysql  Ver 9.1.0 for Linux on x86_64 (MySQL Community Server - GPL)
```

```bash
$ docker exec -it mydb sh
sh-5.1# mysql --user=root --password=$MYSQL_ROOT__PASSWORD --version
mysql: [Warning] Using a password on the command line interface can beinsecure.
mysql  Ver 9.1.0 for Linux on x86_64 (MySQL Community Server - GPL)
sh-5.1#
```