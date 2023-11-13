package com.durys.jakub.happeningservice.happening.domain

import java.time.LocalDateTime
import java.util.*

internal class Happening(private val id: HappeningId, private val place: Place,
                private val period: Period, private var participants: MutableList<Participant> = mutableListOf()) {

    val number = Number(period, place)
    var state = State.New


    enum class State {
        New,
        Invited,
        Closed,
        Archived
    }


    fun invite(participants: List<Participant>) {
        this.participants = participants.toMutableList()
    }


    fun id() = id
    fun state() = state


    companion object Factory {

        fun create(place: String, from: LocalDateTime, to: LocalDateTime, participants: MutableList<Participant>): Happening {
            return Happening(HappeningId(UUID.randomUUID()), Place(place), Period(from, to), participants)
        }

    }

}

internal data class HappeningId(val value: UUID)