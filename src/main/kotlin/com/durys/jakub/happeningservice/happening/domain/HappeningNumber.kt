package com.durys.jakub.happeningservice.happening.domain

internal data class HappeningNumber(private val period: Period, private val place: Place) {

    val value: String = "${place.name} [${period.from.toLocalDate()}-${period.to.toLocalDate()}]"
}