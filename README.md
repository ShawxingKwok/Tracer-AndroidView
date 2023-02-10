# Tracer on Traditional Android

## Note
This extension is for the traditional android framework with `Fragment`. If you are using `compose`, 
you only need to configure as [setup](#setup) but omit the dependency 
`tracer-android-traditional-compiler`. 

## Setup
Configure in your `build.gradle`, regardless of the configuration at [TracerCommon](https://github.com/ApolloKwok/TracerCommon)

Root project:

Module:

Application module:
```groovy
andrid{
    applicationVariants.all {
        def variantName = name
        sourceSets {
            getByName("main") {
                java.srcDir(file("$buildDir/generated/ksp/$variantName/kotlin"))
            }
        }
    }
}
```

Library module:
```groovy
andrid{
    libraryVariants.all {
        def variantName = name
        sourceSets {
            getByName("main") {
                java.srcDir(file("$buildDir/generated/ksp/$variantName/kotlin"))
            }
        }
    }
}
```

### configure tracer
Add this part directly, rather than insert messily.
```groovy
//region tracer
// options
//ksp{
//    arg("tracer.allInternal", "")
//    arg("tracer.propertiesFullName", "")
//}

tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile).configureEach {
    kotlinOptions.freeCompilerArgs += '-Xcontext-receivers'
}

dependencies {
    // Keep this version latest but the prefix can't be higher than your kotlin version. 
    ksp 'io.github.apollokwok:tracer-common-compiler:1.7.20-1.1.0'
    // Never put this above 'tracer-common-compiler`.
    ksp 'io.github.apollokwok:tracer-android-traditional-compiler:1.7.20-1.1.0'
    // Keep this version latest but not higher than the version above. 
    implementation 'io.github.apollokwok:tracer-common-annotations:1.7.20-1.1.0'
}
//endregion 
```