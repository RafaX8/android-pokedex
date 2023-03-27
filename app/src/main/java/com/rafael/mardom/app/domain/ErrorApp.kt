package com.rafael.mardom.app.domain

sealed class ErrorApp {
    object DataError : ErrorApp()
    object NoInternetError : ErrorApp()
    object TimeOutError : ErrorApp()
    object UnknownError : ErrorApp()
}