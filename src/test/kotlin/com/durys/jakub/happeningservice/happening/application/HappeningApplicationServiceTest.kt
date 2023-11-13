package com.durys.jakub.happeningservice.happening.application

import com.durys.jakub.happeningservice.happening.domain.Happening
import com.durys.jakub.happeningservice.happening.domain.HappeningId
import com.durys.jakub.happeningservice.happening.domain.Period
import com.durys.jakub.happeningservice.happening.domain.Place
import com.durys.jakub.happeningservice.happening.domain.command.ArchiveHappeningCommand
import com.durys.jakub.happeningservice.happening.domain.command.InitiateHappeningCommand
import com.durys.jakub.happeningservice.happening.infrastructure.InMemoryHappeningRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class HappeningApplicationServiceTest {

    private val happeningRepository = InMemoryHappeningRepository();
    private val happeningApplicationService = HappeningApplicationService(happeningRepository)


    @Test
    fun shouldCreateHappening() {

        val command = InitiateHappeningCommand("Warsaw",
                LocalDate.of(2023, 1, 1).atStartOfDay(),
                LocalDate.of(2023, 1, 3).atStartOfDay(), mutableListOf())

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
        assertEquals(Happening.State.Archived, loaded.state)
    }

}