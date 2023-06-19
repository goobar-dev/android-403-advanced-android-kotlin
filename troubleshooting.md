# Troubleshooting

## Project fails to build on M1 Mac
This likely means some combination of Gradle, Android Gradle Plugin, Compose, or JVM version are mismatched for the M1 architecture.

The default configuration of the sample project is a known good configuration.
However, if building with a JDK other than the embedded Android Studio JDK, there could be incompatibilities.
Check with the instructor to identify a good configuration for the project and development machine.

Check out the [Compose to Kotlin Compatibility Map](https://developer.android.com/jetpack/androidx/releases/compose-kotlin)
for a breakdown of known compatible version combinations.