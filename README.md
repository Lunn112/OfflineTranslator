# Offline Translator（离线译）

## CI/CD

Push 或 Pull Request 会自动执行 Java 17、Android SDK、Gradle 缓存、单元测试、Lint、Debug/Release 构建，并上传 30 天有效的 APK artifacts。创建 `v*` tag 会自动创建 GitHub Release 并附加 Release APK；`pages.yml` 发布项目文档。

## 架构

Compose UI -> ViewModel -> TranslationRepository -> 插件化 TranslationEngine -> CTranslate2/ONNX。模型、语言包和专业词库均存储在 App 私有目录，翻译不调用云端 API。

## 构建信息与诊断

`BuildConfig` 写入版本号、构建号、Git commit、分支和构建时间。`CrashLogger` 将 Java/Kotlin/Native/模型/JNI/翻译错误写入 `files/logs/crash.log`，可通过 `CrashLogger.export()` 导出。

## v0.1.0 测试版

首次 Push 后，GitHub Actions 的 `Android CI` workflow 会优先执行测试、Lint 和 Debug APK 构建。Debug artifact 下载路径为 `Actions -> Android CI -> OfflineTranslator-Debug -> app-debug.apk`。Release 未配置签名时允许失败，不会阻断 Debug artifact。

## 构建方式

GitHub Actions 使用官方 Gradle Action 安装 Gradle 8.7。若仓库存在 `gradle/wrapper/gradle-wrapper.jar`，workflow 优先执行 `./gradlew`；若 Wrapper JAR 尚未提交，则自动回退到系统 `gradle`，仍可生成 Debug APK。

本地构建可使用：

```bash
gradle assembleDebug
```

当 Wrapper 完整后，也可以使用：

```bash
./gradlew assembleDebug
```

当前版本使用 ONNX Runtime Mobile 接入本地 NLLB 推理边界，并提供语言包管理与校验流程；对话模式、Whisper、TTS 接口可继续扩展。

## 构建
使用 Android Studio Ladybug 或命令行 Gradle，JDK 17、Android SDK 35：`gradlew.bat assembleRelease`。

## 安装
将 `app/build/outputs/apk/release/app-release.apk` 安装到 Android 10+ 设备：`adb install -r app-release.apk`。
