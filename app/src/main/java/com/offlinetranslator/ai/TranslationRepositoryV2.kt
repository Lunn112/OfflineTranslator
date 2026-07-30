package com.offlinetranslator.ai
class TranslationRepositoryV2(private val registry:TranslationEngineRegistry,private val quality:TranslationQualityManager){suspend fun translate(request:TranslationRequest,engineId:String="ctranslate2"):Pair<TranslationResponse,QualityAdvice>{val response=registry.get(engineId).translate(request);return response to quality.assess(request.text,response.text)}}
