package com.durys.jakub.happeningservice.invitation.domain

import com.durys.jakub.happeningservice.happening.domain.HappeningNumber
import com.durys.jakub.happeningservice.sharedkernel.ParticipantId
import java.time.LocalDate

internal class Invitation(private val id: InvitationId, private val validTill: LocalDate,
                          private var reply: InvitationReply) {


    fun reply(confirmation: Boolean) {

        if (LocalDate.now().isAfter(validTill)) {
            throw RuntimeException("Cannot reply to this invitation")
        }

        reply = InvitationReply(confirmation)
    }


    fun id() = id
    fun validTill() = validTill
    fun confirmation() = reply.confirmation
    fun happeningNumber() = id.happeningNumber
    fun participant() = id.participantId


    companion object Factory {
        fun create(participantId: ParticipantId, happeningNumber: HappeningNumber, validTill: LocalDate): Invitation {
            return Invitation(InvitationId(participantId, happeningNumber), validTill, InvitationReply(false))
        }
    }

}

internal data class InvitationId(val participantId: ParticipantId, val happeningNumber: HappeningNumber)