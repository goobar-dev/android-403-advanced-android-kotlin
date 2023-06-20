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
- Start by reviewing the starter code

Set up a basic navigation graph:
- Create a new file `DemoNavigationGraph` and create a composable `DemoNavigationGraph`
- Create a remembered `NavController` by calling `rememberNavController()`
- Setup a `NavHost` with 2 composable destinations
  - One route named `"versionsList"`
  - One route named `"versionDetails/{apiVersion}"`
  - The `versionDetails` route should configure the `apiVersion` argument to be of type `Int`
- Update the `AndroidVersionListScreen` click handler to navigate to the details screen using the `NavController`
- Parse the `apiVersion` nav argument from the `versionDetails` backstack entry and pass to the composable
- Update the `AndroidVersionDetailsScreen` back click handler to pop the `NavController` backstack
- Update `MainActivity` to call `DemoNavigationGraph` instead of `MainActivityContent`
- Remove `HomeScreen.kt` and update `AndroidVersionListScreen` and `AndroidVersionDetailsScreen` to include their own `Scaffold`

Refactor the navigation graph configuration to use a custom type-safe routing implementation:
- Add `kotlinx.serialization` to the project and add `@Serializable` annotation to `AndroidVersionInfo`
- Create a file named `NavigationDestination.kt`
- Create a `NavigationArgs` interface to apply to classes that will be passed along with navigation routes
- Create a `NavigationDestination` interface with `name` and `route` properties
- Create an `ArgumentDestination` interface extending `NavigationDestination`
- Create extension methods on `ArgumentDestination` named `createRouteWithArgs()` and `retrieveArgs()`
- Define object classes to represent our `VersionsList` and `VersionDetails` destinations
- Refactor `DemoNavigationGraph` to use these new type-safe destinations

Refactor the navigation graph implementation to use the animated navigation apis from the Accompanist libraries.
- Add the latest stable `com.google.accompanist:accompanist-navigation-animation` version to the project's `app/build.gradle` file
- Replace `rememberNavController()` and `NavHost` with `rememberAnimatedNavController()` and `AnimatedNavHost()`
- Configure `enterTransition` and `exitTransition` for the version details route
- Use `AnimatedContentScope.SlideDirection.Left` for the enter `towards` value and `AnimatedContentScope.SlideDirection.Right` for the exit animation
- Customize the `animationSpec` using the `tween()` function

## Lesson 5 - MVVM & Compose
Start by populating versions list state using basic ViewModel:
- Create an `AndroidVersionsListViewModel`
- Add a single property `versionsListState: MutableState<List<AndroidVersionInfo>>` and initialize to pull data from `AndroidVersionsRepository`
- Update `AndroidVersionsListScreen` take an instance of `AndroidVersionsListViewModel` as a parameter using the `viewModel()` function to create a new instance by default
- At the beginning of `AndroidVersionListScreen`, initialize a variable `versionsListState` to track the ViewModel property using the mutable state delegate
- Use that state variable, rather than the repository, to populate the `LazyColumn`

Refactor to use `StateFlow` to pass view state to Composables:
- Within `AndroidVersionsListViewModel` create a `State` data class that holds a `List<AndroidVersionViewItem>`
    - Within `State` create a child class named `AndroidVersionViewItem` to hold all data needed to represent a version on the screen
- Create an `AndroidVersionDetailsViewModel`
- Within `AndroidVersionDetailsViewModel` create a `State` data class that holds the display details
- Add a `StateFlow` to `AndroidVersionsListViewModel` to expose an instance of `State`
- Add a `StateFlow` to `AndroidVersionDetailsViewModel` to expose an instance of `State`
- Update `AndoridVersionsDetailsScreen` to take a `AndroidVersionDetailsViewModel` as a parameter
- At the beginning of `AndoridVersionsDetailsScreen`, initialize a variable `state` to track the ViewModel property using the `collectAsState()` function
- Update `AndoridVersionsDetailsScreen` to pull data from the tracked state variable

Implement a sort feature:
- Add an enum named `Sort` with values `ASCENDING` and `DESCENDING`
- `AndroidVersionsListViewModel` should maintain an internal `MutableStateFlow` named `_sort` that tracks the current `Sort` value
- `AndroidVersionsListViewModel` should maintain an internal `MutableStateFlow` named `_versions` that emits the list of verions from `AndroidVersionsRepository`
- The value of `state` should be derived by combining the emissions from `_sort` and `_versions`
- When a value from either flow changes, calculate the output state by sorting the versions list, and mapping to instances of `State.AndroidVerionViewItem`
- Use the `stateIn()` function to convert the combined flows into a `StateFlow`
- Add a "sort" button to `AndroidVersionsListScreen`
- Add a function on `AndroidVersionsListViewModel` named `onSortClicked()` to respond to clicking the sort button

## Lesson 6 - Testing Composables
- Create a new test class `AndroidVersionsListTest` in the `androidTest` source set
- Add a new test rule using `createComposeRule()`
- Create new test method named `versionsListDisplayedOnHomeScreenLoad()`
  - Validate that a composable with `testTag = "Versions List"` exists in the tree
- Create new test method named `versionsListDisplaysFirstVersionInfo()`
  - Validate that the first item in the list matches what is expected from the repository
- Create a new test method named `printTreeToLog()`
  - Use the `printToLog()` method to view the semantics tree
- Create a new test method named `versionInfoClickHandlerCalledWhenCardIsClicked()`
  - Validate that clicks on a list item are propagated to the click handler

## Lesson 7 - Improving Compose Experience
- Refactor `AndroidVersionsListScreen` to include a `AndroidVersionsList` composable that takes a `List<AndroidVersionsListViewModel.State.AndroidVersionViewItem>`
- Write a preview composable for `AndroidVersionsListScreen`
- Write a preview composable for `PreviewAndroidVersionsList` that uses a `PreviewParameterProvider` to generate previews for different sets of data
- Create a multi-preview annotation and apply it to `PreviewAndroidVersionsListScreen()` to generate a multitude of previews across different device configurations

## Lesson 8 - Compose Performance
Examine recomposition counts:
- Use Android Studio's Layout Inspector to examine recomposition counts for the app
- Modify app to simulate a "heartbeat" the generates more frequent recompositions

Analyze recomposition:
- Integrate Compose Compiler metrics
- Generate a Compose Compiler metrics report
- Examine the report to understand what could be causing recompositions
- Update the `State` classes and `AndroidVersionsListScreen` composables to be stable and skippable
- Create a variable `val listState = state.versionList` in `AndroidVersionsListScreen` and observe the impact on recomposition

## Lesson 9 - Custom Design System
- Create a new `ui.theme.custom` package
- Create a new class `CustomColorScheme` and a function `defaultCustomColorScheme()` to create a default instance
    - `CustomColorScheme` should be a data class with one property `val primary: Color`
- Create a new `CustomTheme` object class that has a single `@ReadOnlyComposable` property `val colorScheme: CustomColorScheme`
- Create a `CustomButton` composable that uses a `Box()` to build up a very simply custom button that pulls colors from `CustomTheme`
- Add a usage of `CustomButton` somewhere in the app to demonstrate its usage

## Lesson 10 - Network Requests with Suspend Functions
- Start by examining the updated app structure
- Explore the Hilt integration
- Add a `getTopics(): List<Topic>` function to `StudyGuideService`
- Update `AndroidTopicsViewModel` to expose a `StateFlow<List<TopicViewItem>>`
- Populate the `StateFlow` by using a flow builder to make a network call to fetch topics using `StudyGuideService`
- Add sealed class for `Events` within `AndroidTopicsViewModel`.
    - There should be a single subtype `ShowTopicClickedMessage(val message: String)`
- Add a method to `AndroidTopicsViewModel` `onTopicClicked(topic: TopicViewItem)` to respond to clicking a topic list item
    - This method should emit an event via a `SharedFlow` indicating an action to take in response to clicking a list item
- Update `TopicsScreen` to display the list of `TopicViewItem` from `AndroidTopicsViewModel`
- Show a `Snackbar` in response to any `Event` emitted by `AndroidTopicsViewModel`
- Call `AndroidTopicsViewModel.onTopicClicked()` when a list item is clicked

## Lesson 11 - Local Storage with Room
Save and retrieve notes from a local database:
- Start by observing the new Notes flow in the app
- Update `NoteEntity` to store a `title`, `category` and `content` value with an auto-generated primary key
- Add a `db` package
- Create an interface `NoteDao`
    - Add the `@Dao` annotation
    - Add a `getAll(): Flow<List<NoteEntity>>` method
    - Add a `get(noteId: Int): NoteEntity` method
    - Add a `save(note: NoteEntity)` method
- Create an abstract class `AppDatabase`
    - Make the class extend `RoomDatabase`
    - Add the `@Database` annotation and add `NoteEntity::class` to the `entities` list
    - Add a `abstract fun noteDao(): NoteDao` method
- Create a new class `DatabaseModule` in the `di` package
    - Add a method `provideNoteDao(appDatabase: AppDatabase): NoteDao`
    - Add a method `provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase`
- Update `NotesViewModel` to display the notes from the database
- Update `AddNoteViewModel` to save a new note to the database when the save button is clicked

Encrypt a local Room database:
- Observe the database using the Database Inspector
- Add a `DATABASE_PASSWORD` project property to `app/build.gradle.kts`
- Add a sqlcipher `SupportFactory` variable in `provideAppDatabase()`
- Call `openHelperFactory(supportFactory)` on the database builder
- Try observing the local database again