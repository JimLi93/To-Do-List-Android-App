package com.example.myapplication.components

fun addZero(num: Int): String {
    if( num < 10 ) {
        val ans = "0" + num.toString()
        return ans
    }
    else return num.toString()
}