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

}