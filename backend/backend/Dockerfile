# Używamy obrazu bazowego z zainstalowaną Javą
FROM openjdk:17-oracle
# Ustalamy katalog roboczy w kontenerze
WORKDIR /app

# Kopiujemy plik JAR aplikacji do kontenera
COPY Komunikator/target/Komunikator-0.0.1-SNAPSHOT.jar app.jar

# Określamy port, na którym działa aplikacja
EXPOSE 8080

# Uruchamiamy aplikację po uruchomieniu kontenera
CMD ["java", "-jar", "app.jar"]
