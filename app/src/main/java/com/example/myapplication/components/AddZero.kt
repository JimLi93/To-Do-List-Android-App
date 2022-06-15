package com.example.myapplication.components

fun addZero(num: Int) = if (num < 10) "0$num" else num.toString()