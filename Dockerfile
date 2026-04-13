# Usamos Java 21 como base (la que definimos para el entorno de ejecución)
FROM eclipse-temurin:21-jdk-alpine

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# COPIAMOS el archivo jar que acabamos de generar en tu PC al contenedor
# Nota: Quitamos el "--from build" porque el archivo está en tu disco, no en otro stage
COPY target/*.jar app.jar

# Exponemos el puerto
EXPOSE 8080

# Comando para arrancar la API
ENTRYPOINT ["java", "-jar", "app.jar"]