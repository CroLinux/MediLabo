# Use the existing image
FROM eclipse-temurin:17-jdk-jammy

# Copy the created file 
COPY target/MS-Gateway-*.jar /app/medilabo-msgatewayapp.jar

# Definition of the working directory
WORKDIR /app

# Commande d'entrée pour exécuter l'application Java
CMD ["java", "-jar", "medilabo-msgatewayapp.jar"]