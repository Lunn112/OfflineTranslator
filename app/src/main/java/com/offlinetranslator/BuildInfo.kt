package com.offlinetranslator
data class BuildInfo(val version:String,val buildNumber:Int,val commit:String,val buildTime:String,val branch:String)
fun buildInfo()=BuildInfo(BuildConfig.VERSION_NAME,BuildConfig.VERSION_CODE,BuildConfig.GIT_COMMIT,BuildConfig.BUILD_TIME,BuildConfig.GIT_BRANCH)
