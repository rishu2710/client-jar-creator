# Script to fix the generated client's POM file
# This updates Spring Boot version and other properties to match your main project

Write-Host "Fixing generated client POM file..."

$pomFile = "client-code/pom.xml"

if (Test-Path $pomFile) {
    Write-Host "Found POM file: $pomFile"
    
    # Read the POM content
    $content = Get-Content $pomFile -Raw
    
    # Replace Spring Boot version (be specific)
    $content = $content -replace '<spring-boot-version>.*?</spring-boot-version>', '<spring-boot-version>3.5.4</spring-boot-version>'
    
    # Replace Spring Framework version (be specific)
    $content = $content -replace '<spring.version>.*?</spring.version>', '<spring.version>6.2.9</spring.version>'
    
    # Replace Java version (be specific)
    $content = $content -replace '<java.version>.*?</java.version>', '<java.version>17</java.version>'
    
    # Update parent version ONLY if it's Spring Boot parent (be very specific)
    $content = $content -replace '(<parent>.*?<groupId>org\.springframework\.boot</groupId>.*?<artifactId>spring-boot-starter-parent</artifactId>.*?<version>).*?(</version>)', '$13.5.4$2'
    
    # Write the updated content back
    Set-Content $pomFile $content -Encoding UTF8
    
    Write-Host "✅ POM file updated successfully!"
    Write-Host "   - Spring Boot version: 3.5.4"
    Write-Host "   - Spring Framework version: 6.2.9"
    Write-Host "   - Java version: 17"
    Write-Host "   - Other dependencies preserved"
} else {
    Write-Host "❌ POM file not found: $pomFile"
    Write-Host "Make sure to run the generate-client.ps1 script first!"
}
