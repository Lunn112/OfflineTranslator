package com.offlinetranslator
data class Diagnostics(val engine:String,val model:String,val tokenizer:String,val nativeLoaded:Boolean,val abi:String,val android:String,val threads:Int,val nnapi:Boolean)
