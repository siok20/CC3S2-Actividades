FROM openjdk:17

WORKDIR /app

COPY ./TowerDefense/src/main/java/ /app/src

RUN javac src/cc3s2/towerdefense/*.java -d out

WORKDIR /app/out

CMD ["java", "cc3s2.towerdefense.TowerDefenseGame"]