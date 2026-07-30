package com.offlinetranslator.ai
class TranslationRepository(private val engine:InferenceEngine,private val domains:DomainPackManager){fun translate(text:String,source:String,target:String,domain:Domain?=null):String{var out=engine.infer(text,source,target);if(domain!=null) domains.terms(domain).forEach{(a,b)->out=out.replace(a,b,true)};return out}}
