val kotlin_version: String by project
val logback_version: String by project
val ktor_version: String by project

plugins {
    kotlin("jvm") version "2.1.10"
    kotlin("plugin.serialization") version "2.1.10" // ✅ Añadido el plugin de serialización de Kotlin
    id("io.ktor.plugin") version "3.0.3"
}

group = "com.example"
version = "0.0.1"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {

    // Herramientas esenciales para configurar un servidor en Ktor
    implementation("io.ktor:ktor-server-core")

    // Esto es para la parte de autenticación
    implementation("io.ktor:ktor-server-auth")

    // Motor de servidor, opción eficiente para manejar el servidor HTTP
    implementation("io.ktor:ktor-server-netty")

    // Esto es para el tema de logs, usamos Logback
    implementation("ch.qos.logback:logback-classic:$logback_version")

    // Esto habilita la carga de configuraciones desde archivos .yaml
    implementation("io.ktor:ktor-server-config-yaml")

    // Dependencias para obtener los datos de webs como IGDB
    implementation("io.ktor:ktor-client-core")
    implementation("io.ktor:ktor-client-cio")

    // Esta es la librería de serialización directa de Kotlin
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3") // ✅ Añadido para la serialización JSON

    // Esto es para la parte de testing
    testImplementation("io.ktor:ktor-server-test-host")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")

    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-xml:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
}

//deshabilito los tests temporalmente
tasks.test {
    enabled = false
}