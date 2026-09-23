# Soccer Manager Mobile

Fast, portrait-first football manager game for Android, built with Kotlin and Jetpack Compose.

## Playable MVP

- Manage the starting XI and switch between balanced, attacking, and defensive tactics.
- Simulate a full match with live event feed, possession, score, and momentum.
- Review league standings and upcoming fixtures.
- Track the repository build instructions from the in-game Build Center.

## Build locally

Requires JDK 21, Android SDK 35, and Gradle 8.10.2.

```bash
gradle assembleDebug
```

The debug APK is written to `app/build/outputs/apk/debug/app-debug.apk`.

## GitHub Actions

Every push runs `.github/workflows/android-apk.yml`. The generated APK is available from the workflow's **Artifacts** section as `soccer-manager-debug-apk`.
