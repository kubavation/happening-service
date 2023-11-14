package com.durys.jakub.happeningservice.happening.domain

internal data class HappeningNumber(private val value: String) {

    constructor(period: Period, place: Place): this("${place.name} [${period.from.toLocalDate()}-${period.to.toLocalDate()}]")
}