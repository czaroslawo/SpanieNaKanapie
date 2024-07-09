// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication).apply(false)
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlinAndroid).version("2.0.0")apply(false)
    alias(libs.plugins.kotlinMultiplatform).version("2.0.0").apply(false)
    alias(libs.plugins.jetbrainsCompose) apply false
    alias(libs.plugins.compose.compiler) apply false
    id("com.google.devtools.ksp") version "2.0.0-1.0.21"
    id("de.jensklingenberg.ktorfit") version "2.0.0-beta1"

}