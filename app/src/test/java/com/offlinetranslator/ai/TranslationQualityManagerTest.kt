package com.offlinetranslator.ai
import org.junit.Assert.assertEquals
import org.junit.Test
class TranslationQualityManagerTest { @Test fun detectsLegalDomain(){assertEquals(Domain.LEGAL,TranslationQualityManager().assess("The defendant signed the contract","被告签署合同").recommendDomain)} }
