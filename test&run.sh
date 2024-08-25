#!/bin/zsh

# Esegui i test
mvn test
if [ $? -eq 0 ]; then
    echo "Tests passed. Building the project..."
    # Crea il JAR
    mvn package

    # Controlla se il JAR è stato creato con successo
    if [ $? -eq 0 ]; then
        echo "Build successful. Running the application..."
        # Avvia il JAR
        java -jar target/IS2-1.0-SNAPSHOT.jar
    else
        echo "Build failed."
        exit 1
    fi
else
    echo "Tests failed. Application will not start."
    exit 1
fi