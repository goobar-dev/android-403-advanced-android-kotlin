# Lab 4 - Debug Compose Performance Issues
Gain practice analyzing Jetpack Compose performance

- Examine your app using Android Studio's Layout Inspector

💡 Do you see any unexpected recompositions?

- Integrate Compose Compiler reports for your project and generate a report

💡 Do you see any unexpected results?

💡 Are any of our UI-related classes marked as `unstable`?

💡 Are any of your composables not skippable?

- Fix up any issues you find by using tools such as
    - kotlinx.immutable collections
    - `@Stable` or `@Immutable` annotations
    - moving UI data holders into the same module as the Compose compiler
    - using method references or remembered lambdas for click handlers

## Dev Notes
If you've been building out your own project during Labs 1,2 & 3, feel free to work through this lab in the project you already have set up.

If you missed a lab, or just want to start with a clean slate, you can check out the `lab4-start` tag and start working on Lab4 from there.