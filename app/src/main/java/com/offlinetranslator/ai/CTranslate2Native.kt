package com.offlinetranslator.ai
object CTranslate2Native {
    val available:Boolean = runCatching { System.loadLibrary("ctranslate2_android"); true }.getOrDefault(false)
    external fun create(modelPath:String,tokenizerPath:String,device:String):Long
    external fun destroy(handle:Long)
    external fun translate(handle:Long,sourceTokens:Array<String>,targetLanguage:String):Array<String>
    external fun translateBatch(handle:Long,sourceTokens:Array<Array<String>>,targetLanguage:String):Array<Array<String>>
}
