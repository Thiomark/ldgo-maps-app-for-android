# Ldgo - Maps App for Android

![thumbnail](https://res.cloudinary.com/thiomark/image/upload/v1677488804/portfolio/Ldgo.jpg)
Ldgo is a maps app for Android devices built with Java in Android Studio. It allows users to search for and get directions to different locations, similar to Google Maps. The app uses an online authentication service and a hosted database to store user information, such as account details, preferences, and favorite landmarks.

Here's a short video demonstrating how it works:

[![Assignment POE Demo Video]()](https://res.cloudinary.com/thiomark/video/upload/v1677489778/portfolio/Screen_Recording_20221202_203443_LDGO.mp4 "Assignment POE Demo Video")

## Features
Some of the key features of Ldgo include:

- Interactive maps: Ldgo uses Google Maps API to display an interactive map that users can interact with by zooming in and out, dragging to view different areas, and tapping to select locations.
- Search: Users can search for different locations using the search bar at the top of the screen. Ldgo supports searching for addresses, landmarks, and businesses.
- Directions: Once a user has selected a location, they can get directions to that location from their current location using Ldgo's directions feature. The app supports driving, walking, and public transit directions.
- User accounts: Ldgo allows users to register using the app and create accounts with an online authentication service. Users can login to the app using a username and password to retrieve their account details, preferences, and favorite landmarks stored in a hosted database.
- Settings: Users can change their settings, which are stored in the hosted database, including toggling between the metric and imperial systems for displaying distances, and selecting preferred types of landmarks, such as historical, modern, or popular.
- Nearby landmarks: Ldgo uses a webservice to obtain information about nearby landmarks, which are displayed on the embedded map. The app filters landmarks according to the user's preferred type of landmark based on the setting chosen.
- Current position: The app displays the user's current position on the embedded map.
- Landmark information: Users can select a landmark on the map to get information regarding the landmark, including directions to the landmark. The app calculates the best route between the user's current location and the landmark and displays the estimated time and distance to the destination, as well as the route visually on the embedded map.
- Favorite landmarks: Users can store a list of favorite landmarks, which gets saved to the hosted database.

## Installation
To install Ldgo on your Android device, follow these steps:

1. Clone the repository or download the ZIP file.
2. Open the project in Android Studio.
3. Build the app using the "Run" button or by selecting "Build APK" from the "Build" menu.
4. Transfer the APK file to your Android device and install it.

## Usage
To use Ldgo, follow these steps:

1. Open the app on your Android device.
2. Register for an account or login using your username and password.
3. Use the search bar at the top of the screen to search for a location.
4. Tap on a location to view more information about it, such as its address and ratings.
5. Select "Directions" to get directions to the selected location from your current location.
6. To view your search history or favorite locations, tap on the menu icon in the top-left corner of the screen.
7. To toggle between metric and imperial units or update your preferred type of landmark, go to the settings menu and select "Units" or "Landmarks".
8. To view nearby landmarks filtered by your preferred type of landmark, tap on the "Nearby Landmarks" button on the map.
9. To select a landmark and get information about it, tap on the landmark on the map.
10. To save a landmark to your list of favorites, tap on the "Save" button on the landmark information screen.

## Contributing
If you'd like to contribute to the development of Ldgo, please follow these guidelines:

1. Fork the repository and create a new branch for your changes.
2. Make your changes and test them thoroughly.
3. Submit a pull request with a detailed description of your changes and why they are necessary.
4. Your changes will be reviewed and merged if they meet the project's standards.

## Credits
Ldgo was developed by Itumeleng Tsoela and Siyabona Hadebe as part of the Open Source Coding Intermediate Module. The app uses the following open-source libraries and APIs:

- Retrofit for HTTP requests
- Gson for JSON parsing
- Google Maps API
- Strapi for hosting the database

## License
Ldgo is released under the MIT License. See LICENSE.md for more details.