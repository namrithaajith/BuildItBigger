# BuildItBigger
A joke telling app with free and paid flavors that uses multiple libraries and Google Cloud Endpoints

Project contains a Java library for supplying jokes		
Project contains an Android library with an activity that displays jokes passed to it as intent extras.		
Project contains a Google Cloud Endpoints module that supplies jokes from the Java library. Project loads jokes from GCE module via an async task.		
Project contains connected tests to verify that the async task is indeed loading jokes.		
Project contains paid/free flavors. The paid flavor has no ads, and no unnecessary dependencies.
The free app variant displays interstitial ads between the main activity and the joke-displaying activity.	
The app displays a loading indicator while the joke is being fetched from the server.		
The root build.gradle file contains a task that will start up the GCE dev server, run all Android tests, and shutdown the dev server.
