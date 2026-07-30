package com.offlinetranslator.ai
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
private class Engine:TranslationEngine{override val id="ctranslate2";override suspend fun translate(request:TranslationRequest)=TranslationResponse("ok",.9f,id);override fun close() {}}
class TranslationRepositoryTest{@Test fun routesToSelectedEngine()=runBlocking{val r=TranslationRepositoryV2(TranslationEngineRegistry(mapOf("ctranslate2" to Engine())),TranslationQualityManager());assertEquals("ok",r.translate(TranslationRequest("a","en","zh")).first.text)}}
