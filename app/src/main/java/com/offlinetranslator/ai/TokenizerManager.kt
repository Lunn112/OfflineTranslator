package com.offlinetranslator.ai
data class TokenBatch(val ids:Array<LongArray>,val masks:Array<LongArray>)
interface Tokenizer{fun encode(text:String,sourceLanguage:String):LongArray;fun decode(ids:LongArray,targetLanguage:String):String}
class SentencePieceTokenizer(private val vocabulary:Map<String,Long>):Tokenizer{
 override fun encode(text:String,sourceLanguage:String)=text.trim().split(Regex("\\s+")).map{vocabulary[it]?:0L}.toLongArray()
 override fun decode(ids:LongArray,targetLanguage:String)=ids.joinToString(" "){vocabulary.entries.firstOrNull{e->e.value==it}?.key?"<unk>":"<unk>"}
}
class TokenizerManager{fun create(vocabulary:Map<String,Long>)=SentencePieceTokenizer(vocabulary)}
