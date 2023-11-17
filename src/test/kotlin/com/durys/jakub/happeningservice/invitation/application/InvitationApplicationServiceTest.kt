package com.durys.jakub.happeningservice.invitation.application

import com.durys.jakub.happeningservice.happening.domain.HappeningNumber
import com.durys.jakub.happeningservice.happening.domain.Period
import com.durys.jakub.happeningservice.happening.domain.Place
import com.durys.jakub.happeningservice.invitation.domain.Invitation
import com.durys.jakub.happeningservice.invitation.domain.InvitationAnswer
import com.durys.jakub.happeningservice.invitation.domain.InvitationNumber
import com.durys.jakub.happeningservice.invitation.domain.command.ReplyToInvitationCommand
import com.durys.jakub.happeningservice.invitation.infrastructure.InMemoryInvitationRepository
import com.durys.jakub.happeningservice.pattern.InvitationPatternFactory
import com.durys.jakub.happeningservice.sharedkernel.OptionType
import com.durys.jakub.happeningservice.sharedkernel.ParticipantId
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.util.*

class InvitationApplicationServiceTest {

    private val invitationRepository = InMemoryInvitationRepository()
    private val invitationApplicationService = InvitationApplicationService(invitationRepository)

    @Test
    fun shouldReplyToInvitation() {

        val participantId = ParticipantId(UUID.randomUUID())
        val invitation = addInvitation(participantId)
        val confirmationQuestion = invitationRepository.load(InvitationNumber(invitation.number()))
                .invitationContent()
                .options
                .find { it.type == OptionType.Confirmation }!!

        val command = ReplyToInvitationCommand(InvitationNumber(invitation.number()),
                setOf(
                    InvitationAnswer(confirmationQuestion.id, true)
                ))


        invitationApplicationService.handle(command)

        val saved = invitationRepository.load(InvitationNumber(invitation.number()))
        assertTrue(saved.confirmation())
    }

    private fun addInvitation(participantId: ParticipantId): Invitation {
        val happeningNumber = HappeningNumber(
                Period(LocalDate.of(2023, 1, 1,).atStartOfDay(),
                        LocalDate.of(2023, 1, 2,).atStartOfDay()), Place("Warsaw"))
        val validTill = LocalDate.now().plusDays(1)
        val happeningInvitationPattern = InvitationPatternFactory.default("title")

        val invitation = Invitation.create(participantId, happeningNumber, happeningInvitationPattern, validTill)
        invitationRepository.save(invitation)
        return invitation
    }

}