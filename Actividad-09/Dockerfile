# Usa una imagen base de Python
FROM python:3.11.7

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

#Actualizar pip en wl contenedor
RUN python -m pip install --upgrade pip setuptools wheel

# Copia todo el código del proyecto a la imagen
COPY . .

# Expone el puerto en el que correrá la aplicación (si es una aplicación web o de red)
EXPOSE 3000

# Define el comando para correr la aplicación (modifícalo según lo que necesites)
CMD ["python", "main.py"]
