package com.swapnesh.movies.view.ui.util

import androidx.viewbinding.BuildConfig

class Constants {
    companion object {
        const val DATABASE = "MovieLocalStorage.db"
        const val PREFERENCE = "preference"

        const val BASE_URL_API = "http://www.omdbapi.com/"

        public const val API_KEY = "a18487bc"
        private const val DB_NAME = "movies-db"



        //tags
        const val API_URL_TAG = "API_URL"
        const val API_ERROR_TAG = "API_ERROR"
        const val API_RESPONSE_TAG = "API_RESPONSE"

        //WS Msg
        const val SERVER_ERROR = "Oops! Server Error"
        const val API_NULL_RESPONSE_ERROR = "Oops! Something went wrong"
        const val API_INVALID_RESPONSE_ERROR = "Oops! Got Invalid Response"


        //debug
        const val CUSTOM_DEBUG_TAG = "custom_debug"
        const val CUSTOM_DEBUG_CAT_TAG = "custom_cat_debug"
        var showResponse = BuildConfig.DEBUG
    }
}