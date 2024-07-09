// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication).apply(false)
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlinAndroid).apply(false)
    alias(libs.plugins.kotlinMultiplatform).apply(false)
    id("com.google.devtools.ksp") version "1.9.23-1.0.20"
    id("de.jensklingenberg.ktorfit") version "2.0.0-beta1"

}