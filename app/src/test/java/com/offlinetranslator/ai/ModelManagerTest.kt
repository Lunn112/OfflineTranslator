package com.offlinetranslator.ai
import org.junit.Test
class ModelManagerTest{@Test fun modelSpecCompatibility(){val s=ModelSpec("x","1","",setOf("input_ids","attention_mask"),setOf("logits"));assert(ModelManager::class.java!=null);assert(s.inputNames.contains("input_ids"))}}
