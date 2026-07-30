package com.offlinetranslator.ai
import android.util.Log
object TranslationLogger{private const val TAG="OfflineTranslator";fun modelLoaded(ms:Long,version:String,device:String)=Log.i(TAG,"model_loaded ms=$ms version=$version device=$device");fun inference(ms:Long,batch:Int)=Log.i(TAG,"inference ms=$ms batch=$batch");fun tokenizer(version:String)=Log.i(TAG,"tokenizer version=$version")}
