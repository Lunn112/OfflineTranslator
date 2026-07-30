package com.offlinetranslator
import android.content.Context
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
class CrashLogger(private val context:Context){private val dir get()=File(context.filesDir,"logs").also{it.mkdirs()};fun log(type:String,error:Throwable){val f=File(dir,"crash.log");f.appendText("${SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.US).format(Date())} [$type] ${error.stackTraceToString()}\n")};fun export():File=File(dir,"crash.log")}
