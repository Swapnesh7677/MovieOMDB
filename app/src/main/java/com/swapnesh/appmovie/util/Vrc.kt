package com.triologic.jfbot.utils

import java.util.*

class Vrc(var url: String, val params: MutableMap<String, String> = HashMap()) {

    fun getRequestedUrl(): String {
        var new_url = StringBuilder("$url?")
        for (i in 0 until params.size) {
            val key = params.keys.toTypedArray()[i]
            new_url.append(key).append("=").append(params[key]).append("&")
        }
        new_url = StringBuilder(new_url.substring(0, new_url.length - 1))
        return new_url.toString()
    }
}