# Soccer Manager Mobile PRD

## Problem statement

Build a simple, playable vertical soccer-manager-style Android game in native Kotlin, with a GitHub Actions workflow that builds an APK on every push. The user wants a fast MVP that can be downloaded from Actions and played without unnecessary detail.

## Architecture

- Native Android application using Kotlin, Jetpack Compose, and Material 3.
- Gradle 8.10.2, Android Gradle Plugin 8.7.3, Kotlin 2.0.21, JDK 21, Android SDK 35.
- Single-activity app with local `GameViewModel` state: squad, tactics, match simulation, league table.
- GitHub Actions provisions Java/Android/Gradle and uploads `app-debug.apk` as `soccer-manager-debug-apk`.

## User personas

- A casual football fan who wants a quick manager loop on a phone.
- A developer who needs a reliable APK artifact after each repository push.

## Core requirements (static)

- Portrait-first Android app.
- Squad selection and starting XI management.
- Defensive, balanced, and attacking tactical choices.
- Playable simulated 90-minute match with score, possession, and event timeline.
- League standings and fixtures.
- Automatic debug APK build and artifact upload on every push.

## Implemented (2026-03-04)

- Created the native Kotlin/Compose project structure and Android manifest.
- Added dark obsidian/graphite UI with ember accent, four-tab navigation, and Build Center.
- Added local squad swap flow, tactics, live simulation engine, event feed, table, and fixtures.
- Added `.github/workflows/android-apk.yml` with JDK 21, SDK 35, Gradle 8.10.2, and artifact upload.
- Added build and download instructions to the repository README.

## Prioritized backlog

### P0

- Run the first GitHub Actions build and install the APK on a physical Android device.

### P1

- Persist season progress between launches.
- Update league table after each completed match.
- Add optional player fatigue and substitution events.

### P2

- Add transfer market and club finances.
- Add sound, haptics, and richer match animations.
- Add signed release APK workflow when a release process is available.

## Next tasks

1. Open the `Build Android APK` workflow on the pushed `main` branch.
2. Download `soccer-manager-debug-apk` from the successful Actions run.
3. Test the first APK on Android and fix any device-specific UI issues.