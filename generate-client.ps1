Write-Host "Please start the Spring Boot app in another terminal using: mvn spring-boot:run"
Read-Host "Press Enter once the app is running and /api-docs is accessible..."

# Step 1: Download OpenAPI spec
curl http://localhost:8080/myservice-service/api-docs -o openapi.json

# Step 2: Generate client code with WebClient
java -jar openapi-generator-cli.jar generate -i openapi.json -g java -o client-code --library webclient --additional-properties useJakartaEe=true

# Step 3: Fix the generated POM file
Write-Host "Fixing generated POM file..."
powershell -ExecutionPolicy Bypass -File fix-pom.ps1

# Step 4: Build the client JAR
cd client-code
mvn clean install
