# Pregnancy-Vitals-Tracker
A Jetpack Compose + MVVM Android app for pregnancy health tracking.
It allows users to log vitals (blood pressure, heart rate, weight, baby kicks) and track them in real time.
Additionally, it includes a timer service that runs in the background, even if the app is killed, and continuously updates the UI.

<img src="https://github.com/user-attachments/assets/0cc27659-36d6-4f5b-909d-e9cd48409d6c" width="210" height="450">

## Features

## Part I – Vitals Tracking

* Main Screen: Shows a list of vitals logs in a LazyColumn.
* Add Vitals: A floating action button opens a Compose dialog for new entries:

  - Blood Pressure (Sys/Dia)
  - Heart Rate
  - Weight
  - Baby Kicks Count

* Persistent Storage: Data is saved using Room Database.
* Live Updates: The list updates automatically via StateFlow/LiveData.


## Part II – Background Timer Service

  - Start/Stop Button in main activity.
  - Foreground Service emits current time every second.
  - Time is delivered via Flow/communication channel (no static variables).

* **Robust lifecycle handling**

  -  Keeps running when app is in background.
  -  Continues when app is killed/removed from recents.
  -  Survives configuration changes.
  -  Prevents multiple timers running concurrently.


## Architecture

* UI : Jetpack Compose + Material3.
* State Management: MVVM with Hilt DI.
* Persistence: Room Database for vitals logs.
* Background Work: Foreground Service + Kotlin Flows for timer updates.
* Communication: Service → Activity via Binder & StateFlow.


## Running the Project

* Clone the repo
* Open in Android Studio
* Run the app on an emulator or physical device.


## Technology Used

* Jetpack Compose – Modern UI toolkit.
* Hilt – Dependency Injection.
* Room – Local database.
* StateFlow / LiveData – Reactive UI updates.
* Foreground Service – Persistent background execution.


<img width="720" height="1650" alt="Screenshot_20250906_040328" src=" />
