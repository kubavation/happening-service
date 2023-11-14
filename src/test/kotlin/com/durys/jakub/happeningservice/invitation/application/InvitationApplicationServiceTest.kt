package com.durys.jakub.happeningservice.invitation.application

import com.durys.jakub.happeningservice.happening.domain.HappeningNumber
import com.durys.jakub.happeningservice.happening.domain.Period
import com.durys.jakub.happeningservice.happening.domain.Place
import com.durys.jakub.happeningservice.invitation.domain.Invitation
import com.durys.jakub.happeningservice.invitation.domain.InvitationId
import com.durys.jakub.happeningservice.invitation.domain.command.ReplyToInvitationCommand
import com.durys.jakub.happeningservice.invitation.infrastructure.InMemoryInvitationRepository
import com.durys.jakub.happeningservice.sharedkernel.ParticipantId
import org.junit.jupiter.api.Test
import java.util.*
import org.junit.jupiter.api.Assertions.*
import java.time.LocalDate

class InvitationApplicationServiceTest {

    private val invitationRepository = InMemoryInvitationRepository()
    private val invitationApplicationService = InvitationApplicationService(invitationRepository)

    @Test
    fun shouldReplyToInvitation() {

        val participantId = ParticipantId(UUID.randomUUID())
        val invitation = addInvitation(participantId)
        val command = ReplyToInvitationCommand(participantId, invitation.happeningNumber(), true)

        invitationApplicationService.handle(command)

        val saved = invitationRepository.load(InvitationId(command.participantId, command.happeningNumber))
        assertTrue(saved.confirmation())
    }

    private fun addInvitation(participantId: ParticipantId): Invitation {
        val happeningNumber = HappeningNumber(
                Period(LocalDate.of(2023, 1, 1,).atStartOfDay(),
                        LocalDate.of(2023, 1, 2,).atStartOfDay()), Place("Warsaw"))
        val validTill = LocalDate.now().plusDays(1)

        val invitation = Invitation.create(participantId, happeningNumber, validTill)
        invitationRepository.save(invitation)
        return invitation
    }

}