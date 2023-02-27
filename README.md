# Tracer on Traditional Android

## Note
This extension is for the traditional android framework with `Fragment`.  
If you are using compose, you could still get help from [setup](#setup) and my tutorial on direct
configuration of database, network.

## Setup
Configure your `build.gradle` as below, regardless of the configuration at [TracerCommon](https://github.com/ApolloKwok/TracerCommon)

### add the ksp plugin
In build.gradle(root project):
```groovy
plugins{
    // Assuming your kotlin version is `1.7.21`, here uses the latest ksp plugin version beginning 
    // with `1.7.21` ('1.7.21-1.0.8').  
    id 'com.google.devtools.ksp' version '1.7.21-1.0.8' apply false
}
```
In build.gradle(module):
```groovy
plugins{
    id 'com.google.devtools.ksp'
}
```

### add source sets
Skip to [configure tracer](#configure-tracer) if your ksp plugin version is '1.8.0-1.0.9' or higher.  

If you are using `tracer` in the application module:
```groovy
// Omissible if your ksp plugin version is '1.8.0-1.0.9' or higher. 
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

If you are using `tracer` in an android library module:
```groovy
// Omissible if your ksp plugin version is '1.8.0-1.0.9' or higher. 
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
    // Omit this line if you are using compose. And never put this above 'tracer-common-compiler`.
    ksp 'io.github.apollokwok:tracer-android-traditional-compiler:1.7.20-1.1.0'
    // Keep this version latest but not higher than the version above. 
    implementation 'io.github.apollokwok:tracer-common-annotations:1.7.20-1.1.0'
}
//endregion 
```