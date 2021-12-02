package com.ouvrirdeveloper.domain.models.common

enum class Status(val type: Int) {
    SUCCESS(0),
    LOADING(4),
    INITIAL(5),
    EMPTY(6),
    ERROR(7)
}