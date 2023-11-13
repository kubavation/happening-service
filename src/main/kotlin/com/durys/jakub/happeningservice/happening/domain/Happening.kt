package com.durys.jakub.happeningservice.happening.domain

import com.durys.jakub.happeningservice.happening.domain.event.HappeningArchived
import com.durys.jakub.happeningservice.sharedkernel.ParticipantId
import java.time.Instant
import java.time.LocalDateTime
import java.util.*

internal class Happening(private val id: HappeningId, private val place: Place,
                         private val period: Period, private val happeningNumber: HappeningNumber, private var participants: MutableList<ParticipantId> = mutableListOf()) {
    
    var state = State.New


    enum class State {
        New,
        Invited,
        Closed,
        Archived
    }

    constructor(id: HappeningId, place: Place, period: Period, participants: MutableList<ParticipantId> = mutableListOf())
            : this(id, place, period, HappeningNumber(period, place), participants)


    fun invite(participants: List<ParticipantId>) {
        this.participants = participants.toMutableList()
    }

    fun archive(): HappeningArchived {

        if (state == State.Closed || state == State.Archived) {
            throw RuntimeException("Invalid state for archive operation")
        }

        state = State.Archived
        return HappeningArchived(UUID.randomUUID(), Instant.now(), id)
    }


    fun id() = id
    fun state() = state


    companion object Factory {

        fun create(place: String, from: LocalDateTime, to: LocalDateTime, participants: MutableList<ParticipantId>): Happening {
            return Happening(HappeningId(UUID.randomUUID()), Place(place), Period(from, to), participants)
        }

    }

}

internal data class HappeningId(val value: UUID)