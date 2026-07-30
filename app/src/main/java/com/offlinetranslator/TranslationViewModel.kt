package com.offlinetranslator
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
class TranslationViewModel:ViewModel(){val input=MutableStateFlow("");val result=MutableStateFlow("");fun setResult(value:String){result.value=value}}
