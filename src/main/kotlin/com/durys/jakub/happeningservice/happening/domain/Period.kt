package com.durys.jakub.happeningservice.happening.domain

import java.time.LocalDateTime

data class Period(val from: LocalDateTime, val to: LocalDateTime) {

    init {
        Validator.test(from, to)
    }

    internal object Validator {
        fun test(from: LocalDateTime, to: LocalDateTime) {
            if (from.isAfter(to)) {
                throw RuntimeException("Date from cannot be later than date to")
            }
        }
    }
}