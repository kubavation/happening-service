package com.durys.jakub.happeningservice.invitation.domain

import com.durys.jakub.happeningservice.happening.domain.HappeningNumber
import com.durys.jakub.happeningservice.happening.domain.Period
import com.durys.jakub.happeningservice.happening.domain.Place
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

        val invitation = Invitation.create(participantId, happeningNumber, validTill)

        assertEquals(InvitationId(participantId, happeningNumber), invitation.id())
    }

    @Test
    fun shouldReplyToInvitation() {

        val participantId = ParticipantId(UUID.randomUUID())
        val happeningNumber = HappeningNumber(
                Period(LocalDate.of(2023, 1, 1,).atStartOfDay(),
                        LocalDate.of(2023, 1, 2,).atStartOfDay()), Place("Warsaw"))
        val validTill = LocalDate.now().plusDays(2)

        val invitation = Invitation.create(participantId, happeningNumber, validTill)

        assertDoesNotThrow { invitation.reply(true) }
        assertTrue(invitation.confirmation())
    }

    @Test
    fun shouldNotReplyToInvitation_whenInvitationIsNotValid() {

        val participantId = ParticipantId(UUID.randomUUID())
        val happeningNumber = HappeningNumber(
                Period(LocalDate.of(2023, 1, 1,).atStartOfDay(),
                        LocalDate.of(2023, 1, 2,).atStartOfDay()), Place("Warsaw"))
        val validTill = LocalDate.now().minusDays(1)

        val invitation = Invitation.create(participantId, happeningNumber, validTill)

        val exception = assertThrows(RuntimeException::class.java) { invitation.reply(true) }
        assertEquals("Cannot reply to this invitation", exception.message);
    }

    @Test
    fun shouldCloseInvitation() {

        val participantId = ParticipantId(UUID.randomUUID())
        val happeningNumber = HappeningNumber(
                Period(LocalDate.of(2023, 1, 1,).atStartOfDay(),
                        LocalDate.of(2023, 1, 2,).atStartOfDay()), Place("Warsaw"))
        val validTill = LocalDate.now().plusDays(2)

        val invitation = Invitation.create(participantId, happeningNumber, validTill)
        val closedAt = LocalDate.of(2023, 1, 1)

        invitation.close(closedAt)

        assertEquals(closedAt, invitation.validTill())
    }


}