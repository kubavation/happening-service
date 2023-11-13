package com.durys.jakub.happeningservice.happening.domain

import java.util.*

internal class Happening(private val id: HappeningId, private val place: Place,
                private val period: Period, private var participants: MutableList<Participant> = mutableListOf()) {

    val number = Number(period, place)


    fun invite(participants: List<Participant>) {
        this.participants = participants.toMutableList()
    }


    fun id() = id
}

internal data class HappeningId(val value: UUID)