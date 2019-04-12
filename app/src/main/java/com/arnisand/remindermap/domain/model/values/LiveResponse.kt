package com.arnisand.remindermap.domain.model.values

sealed class LiveResponse<out T> {

    companion object {
        fun <T> success(value: T) = Success(value)
        fun <T> failure(error: Throwable) = Failure<T>(error)
        fun <T> loading() = Progress<T>()
    }
}

data class Success<out T>(val value: T) : LiveResponse<T>()
data class Failure<out T>(val error: Throwable) : LiveResponse<T>()
class Progress<out T> : LiveResponse<T>()