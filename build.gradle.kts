// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    repositories {
        google()
        jcenter()
        mavenCentral()
    }

    dependencies {
        classpath(com.beetlestance.movies.buildsrc.Libs.androidGradlePlugin)
        classpath(com.beetlestance.movies.buildsrc.Libs.Kotlin.gradlePlugin)
        classpath(com.beetlestance.movies.buildsrc.Libs.Google.PlayServices.gmsGoogleServices)
        classpath(com.beetlestance.movies.buildsrc.Libs.Google.Firebase.crashlyticsGradle)
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}


allprojects {
    repositories {
        google()
        jcenter()
    }
}

subprojects {
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            // Enable experimental coroutines APIs, including Flow
            freeCompilerArgs = freeCompilerArgs + listOf(
                "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-Xopt-in=kotlinx.coroutines.FlowPreview",
                "-Xopt-in=kotlin.Experimental",
                "-Xallow-jvm-ir-dependencies",
                "-Xjvm-default=all"
            )

            jvmTarget = "1.8"
        }
    }
}

tasks.create<Delete>("clean") {
    delete = setOf(rootProject.buildDir)
}