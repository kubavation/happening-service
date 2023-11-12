package com.durys.jakub.happeningservice.happening

import java.time.LocalDateTime

data class Period(val from: LocalDateTime, val to: LocalDateTime) {

    init {
        Period.test(from, to)
    }

    internal object Period {
        fun test(from: LocalDateTime, to: LocalDateTime) {
            if (from.isAfter(to)) {
                throw RuntimeException("Date from cannot be later than date to")
            }
        }
    }
}