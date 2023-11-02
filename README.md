# MediLabo
MediLabo Solutions - Project 9 Openclassrooms

# Environment:

You should already have on your system:
- MySQL installed.
- MongoDB installed.
- Docker installed.

# Run the application:

- Download the project in your system.
- Go to the main folder of the project : **MediLabo-release_1.0** .
- Into each microservice’s folder (**MS-*****) run the command « **mvn clean install** »
- At the root of the project **/MediLabo-release_1.0** run the command « **docker compose up** » 
- In the MySQL container (**mysqldb-1**), import the datas/queries from the file : **MS-Patient/src/main/resources/Data_Patient.sql** .
- In the MongoDB container (**medilabo-msnoteapp-db**), import the datas from the file : **MS-Note/src/main/resources/patient_notes.json** , or add them manually via the GUI of the application.
- Start the application and open your browser at : **http://localhost:18082** (credentials: user / user) .

# Green code:
- Simplify the architecture.
- Refactoring of the code.
- Simplify the scenario of the use of the application and access to the datas.
- Use only the required database(s).
- Simplify the data structure(s).
- Save only the needed datas.
