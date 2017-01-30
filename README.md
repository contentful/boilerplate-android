# Hello World, Contentful Style

This repository contains a simple Hello World style example Android app on how to use Contentful.

<img src="https://github.com/contentful/boilerplate-android/blob/master/assets/device-screenshot.png?raw=true" width="200"/>

# Description of the App

Creating the Contentful SDK, requesting all entries from Contentful, filtering some results, and more is part of this simple application. For a more indepth description, please [see the source code of the Main Activity](app/src/main/java/com/contentful/hello/android/MainActivity.java). Please feel free to also checkout the [dependencies](app/build.gradle#L28) and the [permissions](app/src/main/AndroidManifest.xml#L5) needed.

# Downloading and using the App

If you want to download the source code, please hit the little clone button next to this repository, or clone it using the following command line:

```
git clone git@github.com:contentful/boilerplate-android
```
Once you have downloaded it, please feel free to install it using

```
./gradlew installDebug
```
on linux/mac or

```
gradlew.bat installDebug
```
on windows.

This will install the app on a running emulator, or connected device. To execute the sample, look for the app called `Contentful`, featuring this icon:

<img src="https://github.com/contentful/boilerplate-android/blob/master/app/src/main/ic_launcher-web.png?raw=true" width="100"/>

# End

Thanks, that's it. Please feel free to experiment and leave any issues, as bugs, suggestions for spelling mistakes, etc. here at [this repository issue page](https://github.com/contentful/boilerplate-android/issues).

Be excellent to each other!
