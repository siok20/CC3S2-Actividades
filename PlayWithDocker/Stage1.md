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

