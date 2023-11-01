# Use the existing image
FROM eclipse-temurin:17-jdk-jammy

# Copy the created file 
COPY target/MS-Frontend-*.jar /app/medilabo-msfrontendapp.jar

# Definition of the working directory
WORKDIR /app

# Commande d'entrée pour exécuter l'application Java
CMD ["java", "-jar", "medilabo-msfrontendapp.jar"]