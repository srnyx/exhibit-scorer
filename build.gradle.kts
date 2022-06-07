plugins {
    java
    application
    id("com.github.johnrengelman.shadow") version("7.1.2")
}

application.mainClass.set("xyz.srnyx.exhibit_scorer.Main")