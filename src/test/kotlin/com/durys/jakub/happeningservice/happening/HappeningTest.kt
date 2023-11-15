package com.durys.jakub.happeningservice.happening

import com.durys.jakub.happeningservice.happening.domain.*
import com.durys.jakub.happeningservice.sharedkernel.ParticipantId
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class HappeningTest {


    @Test
    fun shouldSuccessfullyCreateHappening() {
        assertDoesNotThrow { Happening(HappeningId(UUID.randomUUID()), Place("Warsaw"),
                Period(LocalDateTime.now(), LocalDateTime.now().plusDays(1))) }
    }

    @Test
    fun shouldArchiveHappening() {

        val happening = Happening(HappeningId(UUID.randomUUID()), Place("Warsaw"),
                Period(LocalDateTime.now(), LocalDateTime.now().plusDays(1)))

        val result = happening.archive()

        assertEquals(happening.id(), result.happeningId)
        assertEquals(Happening.State.Archived, happening.state())
    }

    @Test
    fun shouldOpenHappening() {

        val happening = Happening(HappeningId(UUID.randomUUID()), Place("Warsaw"),
                Period(LocalDateTime.now(), LocalDateTime.now().plusDays(1)))
        val participants = listOf(ParticipantId(UUID.randomUUID()), ParticipantId(UUID.randomUUID()))
        val validTo = LocalDate.of(2023, 10 ,10)

        val result = happening.sendInvitationsTo(participants, validTo)

        assertEquals(Happening.State.Open, happening.state())
        assertEquals(participants, result.participants)
        assertEquals(validTo, result.validTo)
    }

    @Test
    fun shouldCloseHappening() {

        val happening = Happening(HappeningId(UUID.randomUUID()), Place("Warsaw"),
                Period(LocalDateTime.now(), LocalDateTime.now().plusDays(1)))
        val participants = listOf(ParticipantId(UUID.randomUUID()), ParticipantId(UUID.randomUUID()))
        val validTo = LocalDate.of(2023, 10 ,10)
        val closedAt = LocalDate.of(2023, 10, 9);

        happening.sendInvitationsTo(participants, validTo)
        val result = happening.close(closedAt)


        assertEquals(Happening.State.Closed, happening.state())
        assertEquals(closedAt, result.closedAt)
    }

    @Test
    fun shouldThrowExceptionWhileClosingHappening_whenIsNotOpened() {

        val happening = Happening(HappeningId(UUID.randomUUID()), Place("Warsaw"),
                Period(LocalDateTime.now(), LocalDateTime.now().plusDays(1)))
        val participants = listOf(ParticipantId(UUID.randomUUID()), ParticipantId(UUID.randomUUID()))
        val validTo = LocalDate.of(2023, 10 ,10)
        val closedAt = LocalDate.of(2023, 10, 9);

        val exception = assertThrows(RuntimeException::class.java) { happening.close(closedAt) }
        assertEquals("Invalid state for close operation", exception.message)
    }

}