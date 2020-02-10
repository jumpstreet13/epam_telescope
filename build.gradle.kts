// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(GradlePlugins.ANDROID_GRADLE)
        classpath(GradlePlugins.KOTLIN_GRADLE)
        classpath(GradlePlugins.SAFE_ARGS_GRADLE)
        classpath(GradlePlugins.GOOGLE_SERVICES_GRADLE)
        classpath(GradlePlugins.FIREBASE_CRASHLYTIC_GRADLE)
    }
}

plugins {
    id(GradlePluginId.DETEKT) version GradlePluginVersion.DETEKT
    id(GradlePluginId.TELESCOPE_VERSIONING_PLUGIN) version GradlePluginVersion.GIR_VERSIONING_PLUGIN
}

allprojects {
    repositories {
        google()
        jcenter()
        maven("https://jitpack.io")
    }
}

subprojects {
    tasks.withType<Test> {
        maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).takeIf { it > 0 } ?: 1
    }

    apply(plugin = GradlePluginId.DETEKT)

    detekt {
        input = files("src/main/kotlin")
        config = files("$rootDir/detekt/detekt-rule-set.yml")
        parallel = true
    }
}

task("staticCheck") {
    description = "Runs all static checks."

    group = "verification"
    afterEvaluate {
        // Filter modules with "lintDebug" task (non-Android modules do not have lintDebug task)
        val lintTasks = subprojects.mapNotNull { "${it.name}:lintDebug" }

        // Get modules with "testDebugUnitTest" task (app module does not have it)
        val testTasks = subprojects.mapNotNull { "${it.name}:testDebugUnitTest" }
            .filter { it != "app:testDebugUnitTest" }

        // All task dependencies
        val taskDependencies =
            mutableListOf("detekt").also {
                it.addAll(lintTasks)
                it.addAll(testTasks)
            }

        // By defining Gradle dependency all dependent tasks will run before this "empty" task
        dependsOn(taskDependencies)
    }
}
