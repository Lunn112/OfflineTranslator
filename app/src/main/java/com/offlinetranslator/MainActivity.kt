package com.offlinetranslator

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val Blue = Color(0xFF4285F4)
class OfflineTranslatorApp : Application() { override fun onCreate(){super.onCreate();val logger=CrashLogger(this);Thread.setDefaultUncaughtExceptionHandler{_,e->logger.log("Java/Kotlin",e)}} }
class MainActivity : ComponentActivity() { override fun onCreate(state: Bundle?) { super.onCreate(state); setContent { OfflineTranslatorTheme { App() } } } }
@Composable fun OfflineTranslatorTheme(content: @Composable () -> Unit) { MaterialTheme(colorScheme = lightColorScheme(primary = Blue, background = Color.White), content = content) }
@Composable fun App() { var tab by remember { mutableIntStateOf(0) }; Scaffold(bottomBar = { NavigationBar { listOf("Translate","Conversation","Language packs","Domain packs").forEachIndexed { i,t -> NavigationBarItem(tab==i,{tab=i},icon={Text((i+1).toString())},label={Text(t)}) } } }) { p -> when(tab){0->Home(Modifier.padding(p));1->Conversation(Modifier.padding(p));2->Languages(Modifier.padding(p));else->Domains(Modifier.padding(p))} } }
@Composable fun Header(title:String){Row(Modifier.fillMaxWidth().padding(20.dp),horizontalArrangement=Arrangement.SpaceBetween){Text(title,style=MaterialTheme.typography.headlineSmall);Text("Settings")}}
@Composable fun Home(mod:Modifier){var input by remember{mutableStateOf("")};var result by remember{mutableStateOf("")};Column(mod.fillMaxSize()){Header("Offline Translator");Text("Chinese    ⇄    English",Modifier.padding(horizontal=20.dp),color=Blue);OutlinedTextField(input,{input=it},Modifier.fillMaxWidth().padding(20.dp).height(150.dp),shape=RoundedCornerShape(20.dp),placeholder={Text("Enter text...")});Row(Modifier.padding(horizontal=20.dp),horizontalArrangement=Arrangement.spacedBy(12.dp)){OutlinedButton({}){Text("Voice input")};Button({result=translate(input)}){Text("Translate")}};if(result.isNotEmpty())Card(Modifier.fillMaxWidth().padding(20.dp),shape=RoundedCornerShape(20.dp)){Column(Modifier.padding(20.dp)){Text(result,style=MaterialTheme.typography.titleLarge);Text("Copy    Favorite    Play",color=Blue)}}}}
fun translate(s:String)=if(s.isBlank()) "Please enter text" else "Install a local NLLB language pack to translate offline"
@Composable fun Conversation(mod:Modifier){Column(mod.fillMaxSize()){Header("Conversation");Card(Modifier.padding(20.dp).fillMaxWidth(),shape=RoundedCornerShape(20.dp)){Column(Modifier.padding(20.dp)){Text("A - Chinese",color=Blue);Text("你好，你叫什么名字？",style=MaterialTheme.typography.titleLarge);Spacer(Modifier.height(24.dp));Text("B - English",color=Blue);Text("What is your name?",style=MaterialTheme.typography.titleLarge)}}Spacer(Modifier.weight(1f));Button({},Modifier.fillMaxWidth().padding(20.dp).height(56.dp),shape=RoundedCornerShape(28.dp)){Text("Hold to speak")}}}
@Composable fun Languages(mod:Modifier){Column(mod.fillMaxSize()){Header("Language resources");LazyColumn{items(listOf("Chinese" to "Installed","English" to "Installed","Japanese" to "Download 350 MB","Korean" to "Download 280 MB")){PackRow(it.first,it.second)}}}}
@Composable fun Domains(mod:Modifier){Column(mod.fillMaxSize()){Header("Domain packs");LazyColumn{items(listOf("Medical" to "12 MB","Legal" to "18 MB","Finance" to "24 MB","Technology" to "55 MB","Travel" to "16 MB","Business" to "20 MB","Media" to "30 MB")){PackRow(it.first,it.second)}}}}
@Composable fun PackRow(name:String,status:String){Card(Modifier.fillMaxWidth().padding(horizontal=20.dp,vertical=6.dp),shape=RoundedCornerShape(16.dp)){Row(Modifier.padding(16.dp).fillMaxWidth(),horizontalArrangement=Arrangement.SpaceBetween){Column{Text(name,style=MaterialTheme.typography.titleMedium);Text(status,color=Color.Gray)};Button({}){Text(if(status=="Installed")"Remove" else "Download")}}}}
