# ==========================================
# ETAPA 1: Compilar la aplicación en Render (Build)
# ==========================================
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app

# Descargar dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar el código fuente y compilar el archivo .jar
COPY src ./src
RUN mvn package -DskipTests

# ==========================================
# ETAPA 2: Imagen final liviana de ejecución (Runtime)
# ==========================================
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Usuario seguro sin privilegios de root
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Copia el JAR recién generado en la Etapa 1
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080
ENV SPRING_PROFILES_ACTIVE=prod

# Control de memoria RAM para la capa gratuita de Render (512 MB)
ENTRYPOINT ["java", "-XX:+UseG1GC", "-XX:MaxRAMPercentage=75.0", "-jar", "app.jar"]