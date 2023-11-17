package com.durys.jakub.happeningservice.invitation.domain

import com.durys.jakub.happeningservice.happening.domain.HappeningNumber
import com.durys.jakub.happeningservice.happening.domain.Period
import com.durys.jakub.happeningservice.happening.domain.Place
import com.durys.jakub.happeningservice.pattern.InvitationPatternFactory
import com.durys.jakub.happeningservice.sharedkernel.OptionType
import com.durys.jakub.happeningservice.sharedkernel.ParticipantId
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.util.*

class InvitationTest {

    @Test
    fun shouldCreateInvitation() {
        val participantId = ParticipantId(UUID.randomUUID())
        val happeningNumber = HappeningNumber(
                Period(LocalDate.of(2023, 1, 1,).atStartOfDay(),
                        LocalDate.of(2023, 1, 2,).atStartOfDay()), Place("Warsaw"))
        val validTill = LocalDate.now()
        val happeningInvitationPattern = InvitationPatternFactory.default("title")

        val invitation = Invitation.create(participantId, happeningNumber, happeningInvitationPattern, validTill)

        assertEquals(InvitationId(participantId, happeningNumber), invitation.id())
    }

    @Test
    fun shouldReplyToInvitation() {

        val participantId = ParticipantId(UUID.randomUUID())
        val happeningNumber = HappeningNumber(
                Period(LocalDate.of(2023, 1, 1,).atStartOfDay(),
                        LocalDate.of(2023, 1, 2,).atStartOfDay()), Place("Warsaw"))
        val validTill = LocalDate.now().plusDays(2)
        val happeningInvitationPattern = InvitationPatternFactory.default("title")

        val confirmationOption = happeningInvitationPattern.options.find { it.type == OptionType.Confirmation }!!

        val invitation = Invitation.create(participantId, happeningNumber, happeningInvitationPattern, validTill)

        assertDoesNotThrow {
            invitation.reply(
                    setOf(
                        InvitationAnswer(confirmationOption.id, true)
                    )
            )
        }
        assertTrue(invitation.confirmation())
    }

    @Test
    fun shouldNotReplyToInvitation_whenInvitationIsNotValid() {

        val participantId = ParticipantId(UUID.randomUUID())
        val happeningNumber = HappeningNumber(
                Period(LocalDate.of(2023, 1, 1,).atStartOfDay(),
                        LocalDate.of(2023, 1, 2,).atStartOfDay()), Place("Warsaw"))
        val validTill = LocalDate.now().minusDays(1)
        val happeningInvitationPattern = InvitationPatternFactory.default("title")

        val invitation = Invitation.create(participantId, happeningNumber, happeningInvitationPattern, validTill)

        val exception = assertThrows(RuntimeException::class.java) { invitation.reply(emptySet()) }
        assertEquals("Cannot reply to this invitation", exception.message);
    }

    @Test
    fun shouldCloseInvitation() {

        val participantId = ParticipantId(UUID.randomUUID())
        val happeningNumber = HappeningNumber(
                Period(LocalDate.of(2023, 1, 1,).atStartOfDay(),
                        LocalDate.of(2023, 1, 2,).atStartOfDay()), Place("Warsaw"))
        val validTill = LocalDate.now().plusDays(2)
        val happeningInvitationPattern = InvitationPatternFactory.default("title")

        val invitation = Invitation.create(participantId, happeningNumber, happeningInvitationPattern, validTill)
        val closedAt = LocalDate.of(2023, 1, 1)

        invitation.close(closedAt)

        assertEquals(closedAt, invitation.validTill())
    }


}