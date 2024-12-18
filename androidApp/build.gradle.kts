val koin_version = "3.5.6"
val ktorVersion = "2.2.4"
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.compose.compiler)


}

android {
    namespace = "com.example.spanienakanapie"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.spanienakanapie"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        compileSdkPreview = "UpsideDownCake"


        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/*"
        }
    }
    androidComponents {
        onVariants(selector().withBuildType("release")) {
            it.packaging.resources.excludes.add("META-INF/**")
        }

    }



    dependencies {
        implementation("androidx.core:core-ktx:1.9.0")
        implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
        implementation("androidx.activity:activity-compose:1.9.0")
        implementation(platform("androidx.compose:compose-bom:2023.03.00"))
        implementation("androidx.compose.ui:ui:1.6.8")
        implementation("androidx.compose.ui:ui-graphics")
        implementation("androidx.compose.ui:ui-tooling-preview")
        implementation("androidx.compose.material3:material3:1.3.0")
        implementation("androidx.navigation:navigation-compose:2.7.7")
        testImplementation("junit:junit:4.13.2")
        androidTestImplementation("androidx.test.ext:junit:1.1.5")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
        androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
        androidTestImplementation("androidx.compose.ui:ui-test-junit4")
        debugImplementation("androidx.compose.ui:ui-tooling")
        debugImplementation("androidx.compose.ui:ui-test-manifest")


        //icons
        implementation("androidx.compose.material:material-icons-extended:1.6.8")

        //koin
        implementation("io.insert-koin:koin-android:$koin_version")
        implementation("io.insert-koin:koin-androidx-compose:$koin_version")

        //ktor
        //implementation("io.ktor:ktor-http-jvm:2.2.4")
        implementation("io.ktor:ktor-client-core:$ktorVersion")
        implementation("io.ktor:ktor-server-core:$ktorVersion")
        implementation("io.ktor:ktor-server-netty:$ktorVersion")


        implementation(project(":shared"))

        //mapBox
        implementation("com.mapbox.maps:android:11.5.0")
        implementation("com.mapbox.extension:maps-compose:11.5.0")
        implementation("com.mapbox.search:autofill:2.1.0")
        implementation("com.mapbox.search:discover:2.1.0")
        implementation("com.mapbox.search:place-autocomplete:2.1.0")
        implementation("com.mapbox.search:offline:2.1.0")
        implementation("com.mapbox.search:mapbox-search-android:2.1.0")
        implementation("com.mapbox.search:mapbox-search-android-ui:2.1.0")
        implementation("com.mapbox.plugin:maps-scalebar:11.5.0")


      



    }
}