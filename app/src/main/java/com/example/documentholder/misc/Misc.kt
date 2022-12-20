package com.example.documentholder.misc

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

fun Log (cont :String){android.util.Log.d("MyTag",cont)}

val scopeDef = CoroutineScope(Dispatchers.Default)
val scopeMain = CoroutineScope(Dispatchers.Main)
val scopeIO = CoroutineScope(Dispatchers.IO)

