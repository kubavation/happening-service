package com.durys.jakub.happeningservice.happening.domain

data class Number(private val period: Period, private val place: Place) {

    val value: String = "${place.name} [${period.from.toLocalDate()}-${period.to.toLocalDate()}]"
}