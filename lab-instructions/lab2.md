# Lab 2 - Build An Interactive UI With Compose
Build an app that displays a list of Star Wars planets and allows a user to select a planet to view details for the selected planet.

## Deliverables
- Display a top app bar that includes the title of the application
- When the app is opened, display a vertically scrolling list of Star Wards planets
- Clicking a planet list item should open a details screen
- When viewing the details screen, their should be a back button that navigates back to the previous screen
- Navigation between screens should be done using a `NavHost` and `NavController`
- Customize your theme with custom colors, shapes, or typography
- The app should preserve state across configuration change

## Dev Notes
To help get you started, check out the `lab2-start` tag. This will provide you a bootstrapped project that you can start building off of.

Star Wars planet data can be pulled from [SWAPI](https://swapi.dev/)

A `SWAPINetworkClient` has been provided for you in the lab 2 starter code.
You can call `SWAPINetworkClient.getPlanets()` to get a list of planet info.
The `PlanetDTO` object includes a partial set of the available planet data. Feel free to add to it if you want based on the full set of api data.