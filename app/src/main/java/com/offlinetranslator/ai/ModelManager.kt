package com.offlinetranslator.ai
import android.content.Context
import java.io.File
import java.security.MessageDigest

enum class ComputeDevice { CPU, NNAPI }
data class ModelSpec(val id:String,val version:String,val sha256:String,val inputNames:Set<String>,val outputNames:Set<String>,val quantized:Boolean=false)
data class InstalledModel(val spec:ModelSpec,val file:File,val device:ComputeDevice)
class ModelManager(context:Context){
 private val root=File(context.filesDir,"models").also{it.mkdirs()}
 fun installed(spec:ModelSpec):InstalledModel?=File(root,spec.id+"-"+spec.version+".onnx").takeIf{it.isFile&&verify(it,spec.sha256)}?.let{InstalledModel(spec,it,ComputeDevice.CPU)}
 fun install(spec:ModelSpec,source:File,device:ComputeDevice=ComputeDevice.CPU):InstalledModel{require(verify(source,spec.sha256));val dst=File(root,spec.id+"-"+spec.version+".onnx");source.copyTo(dst,true);return InstalledModel(spec,dst,device)}
 fun remove(spec:ModelSpec){File(root,spec.id+"-"+spec.version+".onnx").delete()}
 fun compatible(spec:ModelSpec)=spec.inputNames.containsAll(setOf("input_ids","attention_mask"))
 private fun verify(f:File,expected:String)=expected.isBlank()||MessageDigest.getInstance("SHA-256").digest(f.readBytes()).joinToString(""){ "%02x".format(it)}.equals(expected,true)
}
