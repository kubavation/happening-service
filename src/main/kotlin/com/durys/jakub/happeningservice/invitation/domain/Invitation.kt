package com.durys.jakub.happeningservice.invitation.domain

import com.durys.jakub.happeningservice.happening.domain.HappeningNumber
import com.durys.jakub.happeningservice.sharedkernel.ParticipantId
import java.time.LocalDate

internal class Invitation(private val id: InvitationId, private val validTill: LocalDate) {

    fun id() = id


    companion object Factory {
        fun create(participantId: ParticipantId, happeningNumber: HappeningNumber, validTill: LocalDate): Invitation {
            return Invitation(InvitationId(participantId, happeningNumber), validTill)
        }
    }

}

internal data class InvitationId(val participantId: ParticipantId, val happeningNumber: HappeningNumber)