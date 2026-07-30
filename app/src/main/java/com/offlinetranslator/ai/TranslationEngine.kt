package com.offlinetranslator.ai

data class TranslationRequest(val text:String,val source:String,val target:String)
data class TranslationResponse(val text:String,val confidence:Float,val engineId:String)
interface TranslationEngine:AutoCloseable { val id:String; suspend fun translate(request:TranslationRequest):TranslationResponse }
class NllbTranslationEngine(private val inference:InferenceEngine, private val tokenizer:Tokenizer):TranslationEngine {
 override val id="nllb-onnx"
 override suspend fun translate(request:TranslationRequest):TranslationResponse { val out=inference.infer(request.text,request.source,request.target); return TranslationResponse(out,0.75f,id) }
 override fun close()=inference.close()
}
class TranslationEngineRegistry(private val engines:Map<String,TranslationEngine>){fun get(id:String)=engines[id] ?: error("Engine not installed: $id")}
