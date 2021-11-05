# Android

## java

### com.example

- data

    - db

        - history

            - HistoryItemDao.kt
            - HistoryItemEntity.kt

        - typeConverters

            - LocalDateTimeConverter.kt

        - MainDatabase.kt
        - MigrationFrom1To2.kt

    - HistoryRepositoryImpl.kt
    - SettingsDaoImpl.kt

- di

    - DatabaseProvider.kt
    - HistoryRepositoryProvider.kt
    - SettingsDaoProvider.kt

- domain

    - entity

        - HistoryItem.kt
        - HistoryRepository.kt
        - ResultPanelType.kt

    - CalculateExpression.kt
    - SettingsDao.kt

- presentation

    - common

        - BaseActivity.kt
        - SingleLiveEvent.kt

    - history

        - HistoryActivity.kt
        - HistoryAdapter.kt
        - HistoryResult.kt
        - HistoryViewModel.kt

    - main

        - ExpressionState.kt
        - MainActivity.kt
        - MainViewModel.kt
        - Operator.kt

    - settings

        - SettingsActivity.kt
        - SettingsViewModel.kt

### com.example (test)

- domain

    - CalculateExpressionKtTest.kt

- presentation

    - history

        - HistoryAdapterKtTest.kt

    - main

        - HistoryRepositoryFake.kt
        - MainViewModelTest.kt
        - SettingsDaoFake.kt

    - ExampleUnitTest.kt

## res

### drawable

### layout

- history_activity.xml
- history_item.xml
- main_activity (2)

    - main_activity.xml
    - main_activity.xml (land)

- settings_activity.xml

### values

- colors.xml
- srtings.xml

## build.gradle (:app)

*XMind - Trial Version*