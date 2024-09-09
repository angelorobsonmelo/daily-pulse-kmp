plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.touchlab.skie)
    kotlin("plugin.serialization") version "1.9.20"
    alias(libs.plugins.sqlDelight)
    id("org.kodein.mock.mockmp") version "1.17.0"
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
            linkerOpts("-lsqlite3")
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.bundles.ktor.common)
            implementation(libs.kotlinx.datetime)
            implementation(libs.koin.core)
            implementation(libs.sql.coroutines.extensions)
        }

        androidMain.dependencies {
            implementation(libs.androidx.lifecycle.viewmodel.ktx)
            implementation(libs.ktor.client.android)
            implementation(libs.sql.android.driver)
        }

        getByName("androidUnitTest").dependencies {
            implementation(libs.bundles.shared.androidTest)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
            implementation(libs.sql.native.driver)
        }

        commonTest.dependencies {
            implementation(libs.bundles.shared.commonTest)
        }
    }
}

android {
    namespace = "com.angelorobson.dailypulse"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

sqldelight {
    databases {
        create(name = "DailyPulseDatabase") {
            packageName.set("com.angelorobson.dailypulse.db")
        }
    }
}

mockmp {
    usesHelper = true
    installWorkaround()
}
