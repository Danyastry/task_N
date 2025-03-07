The application should contain a list of videos/clips (at least 10), and clicking on one of the videos will open a new window where you can play the selected video. This window will have the following buttons: switch to the next/previous video, play/pause, and a video playback progress bar (generally a standard set for any video player).

The application architecture should be built on the basis of MVVM or MVI patterns using the components provided in Jetpack. Dependency injection technology should be used (Dagger 2, Hilt, Koin). Data from the remote server should be received using the Retrofit 2 library and cached (not the video files themselves, but information about them) in the database using Room. To work asynchronously with Room and Retrofit, you should use RxJava or Kotlin Coroutines.

To create the UI, you can use both the View system and Jetpack Compose. The player itself should be created using the ExoPlayer or Media3 libraries.
