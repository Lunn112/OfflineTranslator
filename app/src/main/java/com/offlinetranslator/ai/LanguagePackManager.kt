package com.offlinetranslator.ai

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.net.HttpURLConnection
import java.net.URL
import java.security.MessageDigest

enum class PackState { NOT_INSTALLED, DOWNLOADING, INSTALLED, FAILED }
data class LanguagePack(val source: String, val target: String, val sizeBytes: Long, val sha256: String, val url: String? = null)

/** Stores model files under app-private storage and verifies SHA-256 before activation. */
class LanguagePackManager(private val context: Context) {
    private val root get() = File(context.filesDir, "language-packs").also { it.mkdirs() }
    val supportedLanguages = listOf("zh", "en", "ja", "ko")
    fun modelFile(source: String, target: String): File? = File(root, "$source-$target/model.onnx").takeIf { it.isFile }
    fun state(pack: LanguagePack): PackState = when { modelFile(pack.source, pack.target) != null -> PackState.INSTALLED; else -> PackState.NOT_INSTALLED }
    suspend fun installFromFile(pack: LanguagePack, sourceFile: File): PackState = withContext(Dispatchers.IO) {
        val destination = File(root, "${pack.source}-${pack.target}/model.onnx"); destination.parentFile!!.mkdirs()
        sourceFile.copyTo(destination, overwrite = true)
        if (sha256(destination) != pack.sha256) { destination.delete(); PackState.FAILED } else PackState.INSTALLED
    }
    suspend fun download(pack: LanguagePack, onProgress: (Long, Long) -> Unit = { _, _ -> }): PackState = withContext(Dispatchers.IO) {
        val endpoint = pack.url ?: return@withContext PackState.FAILED
        val temp = File(root, "${pack.source}-${pack.target}.part")
        val connection = URL(endpoint).openConnection() as HttpURLConnection
        connection.connect(); if (connection.responseCode !in 200..299) return@withContext PackState.FAILED
        val total = connection.contentLengthLong
        connection.inputStream.use { input -> temp.outputStream().use { output -> val buffer = ByteArray(8192); var done = 0L; var n: Int; while (input.read(buffer).also { n = it } >= 0) { if (n == 0) continue; output.write(buffer, 0, n); done += n; onProgress(done, total) } } }
        installFromFile(pack, temp).also { temp.delete() }
    }
    private fun sha256(file: File): String = MessageDigest.getInstance("SHA-256").digest(file.readBytes()).joinToString("") { "%02x".format(it) }
}
