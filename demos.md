# Demos Outline

## Lesson 1 - What is Jetpack Compose
Make sure the demo project can be built and deployed

- Open up the `demo` app and explore the project setup
- Deploy the project

## Lesson 2 - Building a User Interface with Jetpack Compose
- Create a composable function named `MainActivityContent` to display a list of Android version details
- Use `AndroidVersionsRepository.data` to provide the list of data
- Use a `Scaffold` to provide the basic container for the screen composable
- Configure the `Scaffold` to include a `TopAppBar` that displays the app title
- Use a `LazyColumn` to display a vertically scrolling list of `Cards` displaying the version info
- Clicking a card should display a `Snackbar`
- Change the look of the app by customizing the `MaterialTheme`

## Lesson 3 - State Hoisting
- Use `by rembmer { ... }` to create a variable that holds the currently selected `AndroidVersionInfo`
- If an item is selected, update the `TopAppBar` to show a back button, and the display name of the selected info
- If an item is selected, update the content of `Scaffold` to show an `AndroidVersionDetails` composable
- Refactor the list of `AndroidVersionInfo` into a new composable named `AndroidVersionsList` to display when no info is selected
- Update the `AndroidVersionInfoCard` click handler to set the selected info state
- Set the `onClick` handler for the back icon so clicking back clears the currently selected item
- Update `AndroidVersionInfo` to implement `Parcelable`
- Update the declaration of `selectedItem` to use `rememberSaveable{}` to preserve state across configuration change

## Lesson 4 - Navigation

### Part 1
- Start by reviewing the starter code
    - Examine the use of `AnimatedVisibility()` in `AndroidVersionsListScreen`
- Create a new file `DemoNavigationGraph` and create a composable `DemoNavigationGraph`
- Create a remembered `NavController` by calling `rememberNavController()`
- Set up a `NavHost` with 2 composable destinations
    - One route named `"versionsList"`
    - One route named `"versionDetails/{apiVersion}"`
    - The `versionDetails` route should configure the `apiVersion` argument to be of type `Int`
- Update the `AndroidVersionListScreen` click handler to navigate to the details screen using the `NavController`
- Parse the `apiVersion` nav argument from the `versionDetails` backstack entry and pass to the composable
- Update the `AndroidVersionDetailsScreen` back click handler to pop the `NavController` backstack
- Update `MainActivity` to call `DemoNavigationGraph` instead of `MainActivityContent`
- Remove `HomeScreen.kt` and update `AndroidVersionListScreen` and `AndroidVersionDetailsScreen` to include their own `Scaffold`

### Part 2
- Add the kotlinx serialization plugin, and `kotlinx-serialization-json` dependency to `app/build.gradle`
- Add the `@Serializable` annotation to `AndroidVersionInfo`
- Create a new file in the `home` package named `NavigationDestination.kt`
- Add interfaces for strongly-typed navigation
    - `NavigationArgs`
    - `NavigationDestination`
        - Add `name: String` property
        - Add `route: String` property
    - `ArgumentDesintation<T: NavigationArgs> : NavigationDestination`
- Make `AndroidVersionInfo` implement `NavigationArgs`
- Add extension function for creating an encoded route from a `NavigationArgs`
- Add extension function for retrieving a `NavigationArgs` from a `Bundle`
- Update `AndroidVersionDetailsScreen` to take `AndroidVersionInfo` rather than `Int`
- Update `DemoNavigationGraph` to use the strongly-typed extension functions for navigation

### Part 3
- Add `accompanist-navigation-animation` dependency to `app/build.gradle`
- Update `DemoNavigationGraph` to support animation
    - Replace `rememberNavController()` with `rememberAnimatedNavController()`
    - Replace `NavHost()` with `AnimatedNavHost()`
    - Replace `composable()` with accompanist version
    - Set `enterTransition` and `exitTransition` values for the navigation to/from version details screen