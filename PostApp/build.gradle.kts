buildscript {
  repositories {
    google()
    mavenCentral()
  }
  dependencies {
    classpath("com.android.tools.build:gradle:8.1.0") // Atualize para a versão do seu Gradle
    classpath("com.google.dagger:hilt-android-gradle-plugin:2.45") // Plugin Hilt
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")
  }
}