# SequeniaTestApp

Приложение позволяет:

- просматривать список фильмов с фильтрацией по жанрам
- переходить на экран детальной информации о фильме
- поддерживает обработку ошибок сети

## Стек технологий

### Архитектура:

- **Clean Architecture** (Domain -> Data -> Presentation)
- **MVVM** (Model-View-ViewModel)

### Навигация:

- **Navigation Component + SafeArgs**
    - 1 Activity -> навигация между 2 Fragment'ами
    - FilmListFragment -> FilmDetailFragment

### UI:

- **Jetpack Compose** -> UI внутри Fragment'ов через ComposeView
- XML используется только для Toolbar + базовый layout

### DI (Dependency Injection):

- **Koin** -> для инъекции зависимостей
    - `FilmRepository`  
    - `FilmListViewModel`  
    - `FilmDetailViewModel`

### Networking:

- **Retrofit** -> для работы с API
- **GsonConverterFactory** -> парсинг JSON в DTO

### Работа с изображениями:

- **Coil Compose** -> быстрая и удобная загрузка изображений

### State management:

- **Kotlin Flow / StateFlow** -> для реактивной модели UI
    - наблюдение за состоянием загрузки фильмов
    - обработка ошибок (ErrorBanner)
