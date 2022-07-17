plugins {
    kotlin("jvm") version "1.7.0"
    id("io.papermc.paperweight.userdev") version "1.3.7"
}

group = "io.github.pleahmacaka"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))

    paperDevBundle("1.19-R0.1-SNAPSHOT")
}

val pluginName = rootProject.name.split('-').joinToString("") { it.capitalize() }

extra.apply {
    set("pluginName", pluginName)
    set("packageName", rootProject.name.replace("-", ""))

    set("kotlinVersion", "1.7.0")
}

tasks {
    // generate plugin.yml
    processResources {
        filesMatching("*.yml") {
            expand(project.properties)
            expand(extra.properties)
        }
    }

    register<Copy>("paperJar") {
        from(reobfJar)

        val jarName = "$pluginName.jar"
        rename { jarName }
        val plugins = File("./.debug-server/plugins")
        val plugin = File(plugins, "$pluginName.jar")

        if (plugin.exists()) into(File(plugins, "update"))
        else into(plugins)
    }
}

