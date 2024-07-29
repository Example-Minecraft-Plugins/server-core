import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "7.1.0"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.1"
}

group = "me.davipccunha.tests"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()

    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://papermc.io/repo/repository/maven-public/")
    maven("https://repo.dmulloy2.net/repository/public/")
}

dependencies {
    compileOnly(fileTree("libs") { include("*.jar") })

    compileOnly("net.md-5:bungeecord-chat:1.8-SNAPSHOT")
    compileOnly("org.projectlombok:lombok:1.18.32")
    annotationProcessor("org.projectlombok:lombok:1.18.32")

    compileOnly(fileTree("D:\\Minecraft Dev\\artifacts\\") { include("economy-api.jar") })
    compileOnly(fileTree("D:\\Local Minecraft Server\\plugins") { include("bukkit-utils.jar") })

    compileOnly("com.comphenix.protocol:ProtocolLib:5.1.0")
    implementation("fr.mrmicky:fastboard:2.1.2")
    compileOnly("net.luckperms:api:5.4")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    sourceCompatibility = "11"
    targetCompatibility = "11"
}

tasks.withType<ShadowJar> {
    archiveClassifier.set("")
    archiveFileName.set("${project.name}.jar")

    destinationDirectory.set(file("D:\\Local Minecraft Server\\plugins"))
}

bukkit {
    name = project.name
    prefix = "Server Core" // As shown in console
    apiVersion = "1.8"
    version = "${project.version}"
    main = "me.davipccunha.tests.core.ServerCorePlugin"
    depend = listOf("bukkit-utils")
    softDepend = listOf("economy")
    description = "Core plugin for a server that does multiple small things such as canceling hunger, canceling weather changes and registering essentials commands."
    author = "Davi C"

    commands {
        register("cores") {
            description = "Lista todas as cores disponíveis"
        }

        register("fly") {
            description = "Alterna o modo vôo"
        }

        register("spawn") {
            description = "Teleporta o player para o spawn"
        }

        register("give") {
            description = "Dá um item para o player"
        }
    }
}

