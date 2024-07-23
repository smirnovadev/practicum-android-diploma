# Приложение “Поиск работы” 
## Описание
Дипломный проект представляет собой небольшое приложение для поиска работы,
использующее [API сервиса HeadHunter](https://github.com/hhru/api). Приложение предоставляет следующую функциональность:
- Поиск вакансий;
- Указание фильтров для поиска;
- Просмотр деталей отдельной вакансии;
- И добавление вакансий в список "Избранного".
## Скриншоты
<p align="center">
    <img src="https://github.com/user-attachments/assets/dcde7f88-57ae-4cfb-90f3-4e81e514adce" alt="screenshot1" width="200"/>
    <img src="https://github.com/user-attachments/assets/cc8e76d6-b9a3-49bc-9fc0-c83dc8806810" alt="screenshot2" width="200"/>
    <img src="https://github.com/user-attachments/assets/be8af873-bc86-4c59-9352-b31c03f15b7a" alt="screenshot3" width="200"/>
    <img src="https://github.com/user-attachments/assets/a7e377ed-0f52-420d-8715-94d03a4d1a8d" alt="screenshot4" width="200"/>
</p>


## Приобретенный опыт
Приобрела опыт групповой разработки, настроила CI/CD процесс с использованием GitHub Actions для автоматизации сборки и тестирования приложения, разбила проект по пакетам по принципам Clean Architecture. Использовала GitHub Projects для управления задачами, проводила код-ревью, реализовала функциональность поиска, фильтрации, просмотра деталей и управления избранными вакансиями, работала с API HeadHunter.

## Статический анализ

В проекте настроен базовый статический анализатор - [detekt](https://detekt.dev/).

## Настройка Github Actions

В дипломном проекте используется сервис [Github Actions](https://github.com/features/actions) для настройки CI (
Continuous Integration). Это позволяет автоматизировать базовые проверки качества приложения, такие как компиляция
проекта и прогон статического анализатора [detekt](https://github.com/detekt/detekt). Файл конфигурации CI вы
можете [найти здесь](./.github/workflows/pr_checks.yml).

## Общие требования

- Приложение должно поддерживать устройства, начиная с Android 8.0 (minSdkVersion = 26)
- **IDE**: Android Studio Iguana
- **JDK**: Java 17
- **Kotlin**: 1.7.10
- Приложение поддерживает только портретную ориентацию (`portrait`), при перевороте экрана ориентация не меняется.

## Библиотеки
Room,
Kotlin Coroutines,
Navigation,
Fragment KTX,
Material Components,
Glide,
Gson,
Retrofit,
Koin,
Lifecycle ViewModel KTX,
AppCompat,
ConstraintLayout


