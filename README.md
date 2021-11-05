# Android Calculator on Kotlin, MIPT Project


### Structure of Calculator

```
.
├── .gitignore
├── CalculatorStructure.png
├── README.md
├── app
│   └── src
│       ├── androidTest
│       │   └── java
│       │       └── com
│       │           └── example
│       │               └── myplayground
│       │                   └── ExampleInstrumentedTest.kt
│       ├── main
│       │   ├── AndroidManifest.xml
│       │   ├── java
│       │   │   └── com
│       │   │       └── example
│       │   │           └── myplayground
│       │   │               ├── data
│       │   │               │   ├── HistoryRepositoryImpl.kt
│       │   │               │   ├── SettingsDaoImpl.kt
│       │   │               │   └── db
│       │   │               │       ├── MainDatabase.kt
│       │   │               │       ├── MigrationFrom1To2.kt
│       │   │               │       ├── history
│       │   │               │       │   ├── HistoryItemDao.kt
│       │   │               │       │   └── HistoryItemEntity.kt
│       │   │               │       └── typeConverters
│       │   │               │           └── LocalDateTimeConverter.kt
│       │   │               ├── di
│       │   │               │   ├── DatabaseProvider.kt
│       │   │               │   ├── HistoryRepositoryProvider.kt
│       │   │               │   └── SettingsDaoProvider.kt
│       │   │               ├── domain
│       │   │               │   ├── CalculateExpression.kt
│       │   │               │   ├── SettingsDao.kt
│       │   │               │   └── entity
│       │   │               │       ├── HistoryItem.kt
│       │   │               │       ├── HistoryRepository.kt
│       │   │               │       └── ResultPanelType.kt
│       │   │               └── presentation
│       │   │                   ├── common
│       │   │                   │   ├── BaseActivity.kt
│       │   │                   │   └── SingleLiveEvent.kt
│       │   │                   ├── history
│       │   │                   │   ├── HistoryActivity.kt
│       │   │                   │   ├── HistoryAdapter.kt
│       │   │                   │   ├── HistoryResult.kt
│       │   │                   │   └── HistoryViewModel.kt
│       │   │                   ├── main
│       │   │                   │   ├── ExpressionState.kt
│       │   │                   │   ├── MainActivity.kt
│       │   │                   │   ├── MainViewModel.kt
│       │   │                   │   └── Operator.kt
│       │   │                   └── settings
│       │   │                       ├── SettingsActivity.kt
│       │   │                       └── SettingsViewModel.kt
│       │   └── res
│       │       ├── drawable
│       │       │   ├── ic_arrow_back.xml
│       │       │   ├── ic_backspace_24.xml
│       │       │   ├── ic_history_24.xml
│       │       │   ├── ic_launcher_background.xml
│       │       │   ├── ic_settings_24.xml
│       │       │   └── shape_main_button.xml
│       │       ├── drawable-v24
│       │       │   └── ic_launcher_foreground.xml
│       │       ├── layout
│       │       │   ├── history_activity.xml
│       │       │   ├── history_item.xml
│       │       │   ├── main_activity.xml
│       │       │   └── settings_activity.xml
│       │       ├── layout-land
│       │       │   └── main_activity.xml
│       │       ├── mipmap-anydpi-v26
│       │       │   ├── ic_launcher.xml
│       │       │   └── ic_launcher_round.xml
│       │       ├── mipmap-hdpi
│       │       │   ├── ic_launcher.webp
│       │       │   └── ic_launcher_round.webp
│       │       ├── mipmap-mdpi
│       │       │   ├── ic_launcher.webp
│       │       │   └── ic_launcher_round.webp
│       │       ├── mipmap-xhdpi
│       │       │   ├── ic_launcher.webp
│       │       │   └── ic_launcher_round.webp
│       │       ├── mipmap-xxhdpi
│       │       │   ├── ic_launcher.webp
│       │       │   └── ic_launcher_round.webp
│       │       ├── mipmap-xxxhdpi
│       │       │   ├── ic_launcher.webp
│       │       │   └── ic_launcher_round.webp
│       │       ├── values
│       │       │   ├── colors.xml
│       │       │   ├── strings.xml
│       │       │   └── themes.xml
│       │       └── values-night
│       │           └── themes.xml
│       └── test
│           └── java
│               └── com
│                   └── example
│                       └── myplayground
│                           ├── ExampleUnitTest.kt
│                           ├── domain
│                           │   └── CalculateExpressionKtTest.kt
│                           └── presentation
│                               ├── history
│                               │   └── HistoryAdapterKtTest.kt
│                               └── main
│                                   ├── HistoryRepositoryFake.kt
│                                   ├── MainViewModelTest.kt
│                                   └── SettingsDaoFake.kt
├── build.gradle
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradle.properties
├── gradlew
├── gradlew.bat
├── local.properties
└── settings.gradle
```

