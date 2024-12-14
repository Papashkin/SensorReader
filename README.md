# Sensor Reader
Application that reads data from 2 sensors: accelerometer and gyroscope
# Architecture
Application's architecture is based on MVVM (Model - View - ViewModel), which has 3 different layers: data, domain and resentation.
First layer (data) is used to communicate with sensors and with database;
Seconds layer (domain) is used for UseCases, that keeps business logic and provides data from Data layer to Presentation layer;
Third layer (presentation) handles UI-specific part, which are UI elemens (such views) and viewModels (keep UI logic).
# Technologies
* Jetpack Compose - for UI;
* Coroutines - for parallel executions;
* Room - for database;
* Hiilt - for DI
# Specials
Aplication has 2 buttons - to fetch data and to stop fetching them.
When data is receiving to the app from the sensors, X values of both sensors are visible on the screen.
Flows of data fetching stop working when application goes to background. There are some reasons of that:
1. Battery optimisation;
2. CPU and Memory usage;
3. Avoiding data redundancy;
4. Alignment with app's lifecycle;
5. Background execution limitations.
# Improvements
* Unit-tests;
* CI/CD;
* UI.
