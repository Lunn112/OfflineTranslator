package com.offlinetranslator.ai
import android.content.Context
import java.io.File
enum class Domain{MEDICAL,LEGAL,TECHNOLOGY,FINANCE,BUSINESS,TRAVEL,MEDIA}
class DomainPackManager(context:Context){private val root=File(context.filesDir,"domains").also{it.mkdirs()};fun install(d:Domain,terms:Map<String,String>){File(root,"${d.name}.terms").writeText(terms.entries.joinToString("\n"){it.key+"="+it.value})};fun remove(d:Domain){File(root,"${d.name}.terms").delete()};fun terms(d:Domain)=File(root,"${d.name}.terms").takeIf{it.isFile}?.readLines()?.mapNotNull{it.split("=",limit=2).takeIf{p->p.size==2}?.let{p->p[0] to p[1]}}?.toMap().orEmpty()}
