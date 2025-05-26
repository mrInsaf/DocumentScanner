### Инструкция: Сборка Android APK

---
## Перед началом
### 1. Разверните сервис конвертации файлов (https://github.com/mrInsaf/DocxToPdfConverter)
Для работы приложения необходимо настроить и развернуть сервис DOCX to PDF Converter. Он используется для реализации возможности скачивания и отображения содержимого файлов

### 2. Настройти URL'ы до бэкенда и сервиса конвертации
Для настройки этих путей перейдите в файл
```DocumentScanner\core\src\main\java\com\mrinsaf\core\common\Common.kt``` и настройте соответствующие переменные:


```DOCUMENT_FABRIC_BASE_URL``` - базовый URL до бэкенда


```PDF_CONVERTER_BASE_URL``` - базовый URL до сервиса конвертации (развернутого на предыдущем шаге)

## 📌 Рекомендуемый способ: Сборка через Android Studio

Если вы не хотите вручную устанавливать JDK, Android SDK, Gradle и настраивать переменные среды — просто используйте Android Studio.

### Почему это проще:

* Android Studio автоматически скачает все необходимые компоненты (SDK, build-tools, платформы, Gradle и т.д.)
* Не требуется ручная настройка окружения
* Есть визуальный интерфейс для сборки, запуска и отладки
* Возможность быстро собрать APK нажатием одной кнопки

### Шаги:

1. Скачайте и установите Android Studio: [https://developer.android.com/studio](https://developer.android.com/studio)
2. Откройте ваш проект через `File ➝ Open...`
3. Подождите, пока среда скачает зависимости
4. Для сборки APK выберите:

   ```
   Build ➝ Build Bundle(s) / APK(s) ➝ Build APK(s)
   ```
5. Найдите готовый APK по пути, указанному в уведомлении

> 🔰 Этот способ особенно рекомендуется, если вы не знакомы с CLI-инструментами или хотите сэкономить время.

---

## 1. Установка Java Development Kit (JDK)

Android-сборка требует JDK версии **11**.

### Linux / Debian / Ubuntu:

```bash
sudo apt update
sudo apt install -y openjdk-11-jdk
```

### macOS:

```bash
brew update
brew install openjdk@11
# Добавьте в ~/.zshrc или ~/.bash_profile:
# export JAVA_HOME="$(/usr/libexec/java_home -v11)"
# export PATH="$JAVA_HOME/bin:$PATH"
```

### Windows:

* Скачайте и установите OpenJDK 11 с [https://adoptium.net/](https://adoptium.net/).
* В переменных среды:

  * `JAVA_HOME` = путь к установленному JDK
  * Добавить в `PATH`: `%JAVA_HOME%\bin`

Проверка:

```bash
java -version
```

---

## 2. Установка Android SDK Command-Line Tools

### Установка вручную:

1. Скачайте архив: [https://developer.android.com/tools](https://developer.android.com/tools)
2. Распакуйте:

   * Linux/macOS: `$HOME/Android/Sdk/cmdline-tools/latest/`
   * Windows: `C:\Android\Sdk\cmdline-tools\latest\`

### Переменные среды:

**Linux/macOS:**

```bash
export ANDROID_SDK_ROOT="$HOME/Android/Sdk"
export PATH="$ANDROID_SDK_ROOT/cmdline-tools/latest/bin:$ANDROID_SDK_ROOT/platform-tools:$PATH"
```

**Windows:**

* `ANDROID_SDK_ROOT` = `C:\Android\Sdk`
* В `PATH` добавить:

  * `C:\Android\Sdk\cmdline-tools\latest\bin`
  * `C:\Android\Sdk\platform-tools`

---

## 3. Установка SDK-компонентов через sdkmanager

```bash
yes | sdkmanager --licenses
sdkmanager "platform-tools" \
           "platforms;android-33" \
           "build-tools;33.0.2"
```

---

## 4. Установка Gradle

### Вариант A: Использовать Gradle Wrapper

Если в проекте есть `gradlew` и `gradlew.bat` — ничего не нужно устанавливать.

### Вариант B: Установить вручную

1. Скачайте с [https://gradle.org/releases/](https://gradle.org/releases/)
2. Распакуйте и добавьте в `PATH`, например:

```bash
export GRADLE_HOME=/opt/gradle/gradle-8.2
export PATH=$GRADLE_HOME/bin:$PATH
```

Проверка:

```bash
gradle -v
```

---

## 5. Подготовка проекта

Перейдите в корень проекта:

```bash
cd /путь/к/проекту
```

Если нет `gradlew`, создайте его:

```bash
gradle wrapper --gradle-version 8.2
```

---

## 6. Сборка APK

### Debug APK:

```bash
./gradlew assembleDebug
```

### Release APK (при наличии signingConfig):

```bash
./gradlew assembleRelease
```

APK будут находиться в:

```
app/build/outputs/apk/debug/app-debug.apk
app/build/outputs/apk/release/app-release.apk
```

---

## 7. Установка на устройство

### Установите APK:

```bash
adb install -r app/build/outputs/apk/debug/app-debug.apk
```

### Запустите приложение:

```bash
adb shell am start -n your.package.name/.MainActivity
```

---

## Резюме:

### ✅ Самый простой способ:

* Установить Android Studio
* Открыть проект и нажать **Build APK**

### ⚙️ Альтернатива — ручная сборка:

1. Установить JDK 11
2. Скачать Android SDK CLI
3. Установить нужные SDK пакеты
4. Использовать Gradle или Wrapper
5. Выполнить сборку через `./gradlew assembleDebug`
6. Установить APK через `adb`
