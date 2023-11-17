package com.durys.jakub.happeningservice.invitation.domain

import com.durys.jakub.happeningservice.happening.domain.HappeningNumber
import com.durys.jakub.happeningservice.sharedkernel.HappeningInvitationPattern
import com.durys.jakub.happeningservice.sharedkernel.ParticipantId
import java.time.LocalDate
import java.util.UUID

internal class Invitation(private val id: InvitationId, private var validTill: LocalDate,
                          private val invitationNumber: InvitationNumber,
                          private val invitationContent: HappeningInvitationPattern,
                          private var reply: InvitationReply? = null) {


    fun reply(answers: Set<InvitationAnswer>) {

        if (LocalDate.now().isAfter(validTill)) {
            throw RuntimeException("Cannot reply to this invitation")
        }

        reply = InvitationReplyResolver().resolve(answers, invitationContent)
    }

    fun close(closedAt: LocalDate) {
        validTill = closedAt
    }



    fun id() = id
    fun validTill() = validTill
    fun confirmation() = reply?.confirmation ?: false
    fun happeningNumber() = id.happeningNumber
    fun participant() = id.participantId
    fun number() = invitationNumber.value
    fun invitationContent() = invitationContent


    companion object Factory {
        fun create(participantId: ParticipantId, happeningNumber: HappeningNumber,
                   invitationContent: HappeningInvitationPattern, validTill: LocalDate): Invitation {
            return Invitation(InvitationId(participantId, happeningNumber), validTill, InvitationNumber(UUID.randomUUID()), invitationContent)
        }
    }

}

internal data class InvitationId(val participantId: ParticipantId, val happeningNumber: HappeningNumber)