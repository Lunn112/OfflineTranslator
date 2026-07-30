package com.offlinetranslator.ai

/** Catalog is data-only; URLs are optional and must point to user-approved model files. */
object LanguagePackCatalog {
    val packs = listOf(
        LanguagePack("zh", "en", 0L, "", null),
        LanguagePack("en", "zh", 0L, "", null),
        LanguagePack("zh", "ja", 0L, "", null),
        LanguagePack("ja", "zh", 0L, "", null),
        LanguagePack("zh", "ko", 0L, "", null),
        LanguagePack("ko", "zh", 0L, "", null)
    )
}
