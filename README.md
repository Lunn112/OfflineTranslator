# Offline Translator

Offline Translator is a privacy-first Android translation client with a Google Translate-inspired Material 3 interface. All translation assets are designed to run locally; no translation cloud API is used.

## Features

- Material 3 Compose UI with conversation mode
- MVVM and Repository boundaries
- Pluggable Translation Engine architecture
- CTranslate2 integration boundary
- Language Pack and Domain Pack managers
- Model Marketplace and Download Center foundations
- Diagnostics, crash logging, and GitHub Actions CI

## Screenshots

Screenshots are reserved in [`docs/screenshots`](docs/screenshots):

![Home](docs/screenshots/home.png)
![Conversation](docs/screenshots/conversation.png)
![Language packs](docs/screenshots/language_pack.png)
![Model marketplace](docs/screenshots/model_marketplace.png)
![Settings](docs/screenshots/settings.png)

## Architecture

```text
UI -> ViewModel -> Repository -> TranslationEngine -> CTranslate2 -> SentencePiece -> Result
```

## Project Structure

```text
app/src/main/java/com/offlinetranslator/
├── ai/          # engines, model/language/domain packs, repository
├── data/        # Room and FTS persistence
├── MainActivity.kt
├── BuildInfo.kt
└── CrashLogger.kt
app/src/main/cpp/ # JNI bridge and CMake target
.github/workflows/ # CI, release, Pages
```

## Requirements

Android 10+ (API 29), Android SDK 35, Java 17, Kotlin 2.0.21, Jetpack Compose, AGP 8.5.2, Gradle 8.7.

## Build

GitHub Actions builds Debug APKs on push, pull request, or manual dispatch. Download `OfflineTranslator-Debug` from the workflow Artifacts. Locally use `gradle assembleDebug`; when a complete Wrapper is available use `./gradlew assembleDebug`.

## Current Status

**v0.1.0 Preview** includes UI, MVVM, Repository, plugin engine, language/domain pack foundations, marketplace, diagnostics, and CI. CTranslate2 and SentencePiece native libraries are not bundled yet; the app reports `Translation Engine Not Installed` instead of crashing.

## Roadmap

- v0.2: real offline translation
- v0.3: OCR, camera and PDF translation
- v1.0: production release

## License

MIT License. See [LICENSE](LICENSE).

## Contributing

Please use Issues for bugs and feature requests, and Pull Requests for changes.

## Acknowledgements

Android, Jetpack Compose, Kotlin, ONNX Runtime, CTranslate2, SentencePiece, SQLite, and GitHub Actions.

## Disclaimer

This project is for learning and technical research.
