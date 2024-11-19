#Usando la imagen oficial de Node.js
FROM node:14

#Establece el directorio de trabajo contenedor 
WORKDIR /app

#Copia los archivos del package.json y package-lock.json
COPY package*.json ./

#Instala dependencias
RUN npm install

#Copia el resto de los archivos de la aplicacion
COPY . .

#Expone el puerto en el que la aplicacion correra
EXPOSE 3000

#Comando para iniciar la aplicacion 
CMD ["node", "src/app.js"]
