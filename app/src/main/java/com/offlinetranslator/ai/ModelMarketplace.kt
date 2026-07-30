package com.offlinetranslator.ai
data class MarketplaceModel(val id:String,val name:String,val version:String,val languages:String,val sizeBytes:Long,val sha256:String,val downloadUrl:String?)
class ModelMarketplace(private val modelManager:ModelManager){fun catalog()=listOf(MarketplaceModel("nllb-600m","NLLB-200 distilled 600M","1.0","zh,en,ja,ko",0,"",null));fun installed(m:MarketplaceModel)=modelManager.installed(ModelSpec(m.id,m.version,m.sha256,setOf("input_ids","attention_mask"),setOf("logits")))!=null}
