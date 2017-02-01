# Hello World, Contentful Style

This is a sample app that shows you how to get started using Contentful with an Android application.

<img src="https://github.com/contentful/boilerplate-android/blob/master/assets/device-screenshot.png?raw=true" width="200"/>

## Description of the app

The app creates a client to the Contentful API, requests all entries from a space, and then filters the results based on two criteria. For more details [browse the source code of the Main Activity](app/src/main/java/com/contentful/hello/android/MainActivity.java), the [dependencies](app/build.gradle#L28) and the [permissions](app/src/main/AndroidManifest.xml#L5) needed.

## Download and use the app

If you want to download the source code of the application, click the little clone button next to this repository, or clone it using the following command:

```bash
git clone git@github.com:contentful/boilerplate-android
```

Once it's downloaded, run it with:

```bash
./gradlew installDebug
```

On Linux/Mac, or:

```bash
gradlew.bat installDebug
```

On Windows.

This will install the app on a running emulator, or connected device. To run the app from the launcher, look for the app called 'Contentful' with this icon:

<img src="https://github.com/contentful/boilerplate-android/blob/master/app/src/main/ic_launcher-web.png?raw=true" width="100"/>

## End

That's it. Feel free to experiment and leave any issues, bugs, suggestions for spelling mistakes, etc. via [the repository issue page](https://github.com/contentful/boilerplate-android/issues).

Be excellent to each other!
