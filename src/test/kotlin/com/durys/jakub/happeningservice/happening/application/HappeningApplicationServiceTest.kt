package com.durys.jakub.happeningservice.happening.application

import com.durys.jakub.happeningservice.sharedkernel.InvitationQuestion
import com.durys.jakub.happeningservice.events.DomainEventPublisher
import com.durys.jakub.happeningservice.happening.domain.Happening
import com.durys.jakub.happeningservice.happening.domain.HappeningId
import com.durys.jakub.happeningservice.happening.domain.Period
import com.durys.jakub.happeningservice.happening.domain.Place
import com.durys.jakub.happeningservice.happening.domain.command.AppendInvitationPatternCommand
import com.durys.jakub.happeningservice.happening.domain.command.ArchiveHappeningCommand
import com.durys.jakub.happeningservice.happening.domain.command.CloseHappeningCommand
import com.durys.jakub.happeningservice.happening.domain.command.InitiateHappeningCommand
import com.durys.jakub.happeningservice.happening.domain.command.OpenHappeningCommand
import com.durys.jakub.happeningservice.happening.infrastructure.InMemoryHappeningRepository
import com.durys.jakub.happeningservice.sharedkernel.ParticipantId
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.kotlin.any
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class HappeningApplicationServiceTest {

    private val happeningRepository = InMemoryHappeningRepository()
    private val eventsPublisher = Mockito.mock(DomainEventPublisher::class.java)
    private val happeningApplicationService = HappeningApplicationService(happeningRepository, eventsPublisher)


    @Test
    fun shouldCreateHappening() {

        val command = InitiateHappeningCommand("Warsaw",
                LocalDate.of(2023, 1, 1).atStartOfDay(),
                LocalDate.of(2023, 1, 3).atStartOfDay())

        val happeningId = happeningApplicationService.handle(command)

        assertNotNull(happeningRepository.load(happeningId))
    }

    @Test
    fun shouldArchiveHappening() {

        val happening = Happening(HappeningId(UUID.randomUUID()), Place("Warsaw"),
                Period(LocalDateTime.now(), LocalDateTime.now().plusDays(1)))
        happeningRepository.save(happening)


        happeningApplicationService.handle(ArchiveHappeningCommand(happening.id()));


        val loaded = happeningRepository.load(happening.id())
        assertEquals(Happening.State.Archived, loaded.state())
        verify(eventsPublisher, times(1)).publish(any())
    }

    @Test
    fun shouldOpenHappening() {

        val openTill = LocalDate.of(2023, 10, 10)
        val participants = listOf(ParticipantId(UUID.randomUUID()), ParticipantId(UUID.randomUUID()))
        val happening = Happening(HappeningId(UUID.randomUUID()), Place("Warsaw"),
                Period(LocalDateTime.now(), LocalDateTime.now().plusDays(1)))
        happeningRepository.save(happening)


        happeningApplicationService.handle(OpenHappeningCommand(happening.id(), participants, openTill));


        val loaded = happeningRepository.load(happening.id())
        assertEquals(Happening.State.Open, loaded.state())
        verify(eventsPublisher, times(1)).publish(any())
    }


    @Test
    fun shouldCloseHappening() {

        val happening = Happening(HappeningId(UUID.randomUUID()), Place("Warsaw"),
                Period(LocalDateTime.now(), LocalDateTime.now().plusDays(1)), state = Happening.State.Open)
        val closedAt = LocalDate.of(2023, 10, 9)
        happeningRepository.save(happening)


        happeningApplicationService.handle(CloseHappeningCommand(happening.id(), closedAt))


        val loaded = happeningRepository.load(happening.id())
        assertEquals(Happening.State.Closed, loaded.state())
        verify(eventsPublisher, times(1)).publish(any())
    }


    @Test
    fun shouldAppendInvitationPattern() {

        val happening = Happening(HappeningId(UUID.randomUUID()), Place("Warsaw"),
                Period(LocalDateTime.now(), LocalDateTime.now().plusDays(1)))
        happeningRepository.save(happening)

        happeningApplicationService.handle(AppendInvitationPatternCommand(happening.id(), "Title",
                "Description", setOf(InvitationQuestion("Question1", true))))


        val loaded = happeningRepository.load(happening.id())
        assertNotNull(loaded.invitationPattern())
    }

}