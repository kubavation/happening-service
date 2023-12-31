package com.durys.jakub.happeningservice.happening.domain

import com.durys.jakub.happeningservice.sharedkernel.invitation.content.InvitationQuestion
import com.durys.jakub.happeningservice.happening.domain.event.HappeningArchived
import com.durys.jakub.happeningservice.happening.domain.event.HappeningClosed
import com.durys.jakub.happeningservice.happening.domain.event.HappeningOpened
import com.durys.jakub.happeningservice.pattern.InvitationPatternFactory
import com.durys.jakub.happeningservice.sharedkernel.invitation.content.InvitationContent
import com.durys.jakub.happeningservice.sharedkernel.ParticipantId
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

internal class Happening(private val id: HappeningId, private val place: Place, private val period: Period,
                         private val happeningNumber: HappeningNumber,
                         private var invitationPattern: InvitationContent,
                         private var state: State = State.New) {

    private var openTill: LocalDate? = null

    enum class State {
        New,
        Open,
        Closed,
        Archived
    }

    constructor(id: HappeningId, place: Place, period: Period, state: State = State.New)
            : this(id, place, period, HappeningNumber(period, place), InvitationPatternFactory.default(place.name), state)


    fun appendPattern(title: String, description: String, options: Set<InvitationQuestion>) {

        if (state != State.New) {
            throw RuntimeException("Invalid state for appending invitation pattern")
        }

        invitationPattern = InvitationContent(title, description, options)
    }

    fun sendInvitationsTo(participants: List<ParticipantId>, validTo: LocalDate): HappeningOpened {

        if (state != State.New) {
            throw RuntimeException("Invalid state for send invitations operation")
        }

        state = State.Open
        openTill = validTo

        return HappeningOpened(UUID.randomUUID(), Instant.now(), happeningNumber, participants, validTo, invitationPattern)
    }

    fun archive(): HappeningArchived {

        if (state == State.Closed || state == State.Archived) {
            throw RuntimeException("Invalid state for archive operation")
        }

        state = State.Archived
        return HappeningArchived(UUID.randomUUID(), Instant.now(), id)
    }

    fun close(closedAt: LocalDate): HappeningClosed {

        if (state != State.Open) {
            throw RuntimeException("Invalid state for close operation")
        }

        state = State.Closed
        openTill = closedAt
        return HappeningClosed(UUID.randomUUID(), Instant.now(), happeningNumber, closedAt)
    }


    fun id() = id
    fun state() = state
    fun invitationPattern() = invitationPattern
    fun at() = period
    fun place() = place


    companion object Factory {
        fun create(place: String, from: LocalDateTime, to: LocalDateTime): Happening {
            return Happening(HappeningId(UUID.randomUUID()), Place(place), Period(from, to))
        }
    }

}

internal data class HappeningId(val value: UUID)