package com.merveoz.capstone1.common

//out ifadesi T ile dışarıya data taşıyacağımızı ifade eder
sealed class Resource<out T : Any> {
    data class Success<out T : Any>(val data: T) : Resource<T>()
    data class Error(val errorMessage: String) : Resource<Nothing>()
    data class Fail(val failMessage: String) : Resource<Nothing>()
}
