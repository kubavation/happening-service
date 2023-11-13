package com.durys.jakub.happeningservice.happening.application

import com.durys.jakub.happeningservice.happening.domain.command.InitiateHappeningCommand
import com.durys.jakub.happeningservice.happening.infrastructure.InMemoryHappeningRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDate

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

}