package com.offlinetranslator.ai
data class QualityAdvice(val confidence:Float,val recommendDomain:Domain?,val recommendEngine:Boolean)
class TranslationQualityManager {
 fun assess(source:String,result:String):QualityAdvice { val confidence=(0.5f+(result.length.coerceAtMost(120)/240f)).coerceIn(0f,1f); val domain=when{Regex("defendant|plaintiff|contract",RegexOption.IGNORE_CASE).containsMatchIn(source)->Domain.LEGAL;Regex("inference|model|neural",RegexOption.IGNORE_CASE).containsMatchIn(source)->Domain.TECHNOLOGY;Regex("patient|diagnosis|heart attack",RegexOption.IGNORE_CASE).containsMatchIn(source)->Domain.MEDICAL;else->null};return QualityAdvice(confidence,domain,confidence<0.55f)}
}
