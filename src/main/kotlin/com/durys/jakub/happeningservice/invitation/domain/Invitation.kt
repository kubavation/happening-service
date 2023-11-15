package com.durys.jakub.happeningservice.invitation.domain

import com.durys.jakub.happeningservice.happening.domain.HappeningNumber
import com.durys.jakub.happeningservice.sharedkernel.ParticipantId
import java.time.LocalDate
import java.util.UUID

internal class Invitation(private val id: InvitationId, private val validTill: LocalDate,
                          private val invitationNumber: InvitationNumber,
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
    fun number() = invitationNumber.value


    companion object Factory {
        fun create(participantId: ParticipantId, happeningNumber: HappeningNumber, validTill: LocalDate): Invitation {
            return Invitation(InvitationId(participantId, happeningNumber), validTill,
                    InvitationNumber(UUID.randomUUID()),  InvitationReply(false))
        }
    }

}

internal data class InvitationId(val participantId: ParticipantId, val happeningNumber: HappeningNumber)