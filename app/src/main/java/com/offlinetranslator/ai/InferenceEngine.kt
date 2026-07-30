package com.offlinetranslator.ai
import com.microsoft.onnxruntime.*
import java.util.concurrent.Executors
class InferenceEngine(private val model:InstalledModel,private val tokenizer:Tokenizer):AutoCloseable{
 private val env=OrtEnvironment.getEnvironment();private val pool=Executors.newFixedThreadPool(2);private val session=env.createSession(model.file.absolutePath,OrtSession.SessionOptions())
 fun infer(text:String,source:String,target:String):String=pool.submit<String>{val ids=tokenizer.encode(text,source);val mask=LongArray(ids.size){1};val a=OnnxTensor.createTensor(env,arrayOf(ids));val m=OnnxTensor.createTensor(env,arrayOf(mask));a.use{m.use{session.run(mapOf("input_ids" to a,"attention_mask" to m)).use{tokenizer.decode((it[0].value as Array<*>)[0] as LongArray,target)}}}}.get()
 fun inferBatch(texts:List<String>,source:String,target:String)=texts.map{infer(it,source,target)}
 override fun close(){session.close();pool.shutdownNow();env.close()}
}
