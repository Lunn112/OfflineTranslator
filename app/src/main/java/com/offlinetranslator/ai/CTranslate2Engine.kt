package com.offlinetranslator.ai
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
class CTranslate2Engine(private val model:InstalledModel,private val tokenizer:Tokenizer):TranslationEngine {
 override val id="ctranslate2";private var handle:Long=0
 init {if(!CTranslate2Native.available) throw TranslationException.NativeUnavailable(UnsatisfiedLinkError("libctranslate2_android.so"));handle=CTranslate2Native.create(model.file.parentFile!!.absolutePath,model.file.parentFile!!.absolutePath,model.device.name)}
 override suspend fun translate(request:TranslationRequest):TranslationResponse=withContext(Dispatchers.Default){val start=System.nanoTime();try{val tokens=tokenizer.encode(request.text,request.source).map{it.toString()}.toTypedArray();val result=CTranslate2Native.translate(handle,tokens,request.target).joinToString(" ");TranslationLogger.inference((System.nanoTime()-start)/1_000_000,1);TranslationResponse(result,0.8f,id)}catch(e:OutOfMemoryError){throw TranslationException.OutOfMemory()}catch(e:Throwable){throw TranslationException.InferenceFailed(e)}}
 override fun close(){if(handle!=0L){CTranslate2Native.destroy(handle);handle=0}}
}
