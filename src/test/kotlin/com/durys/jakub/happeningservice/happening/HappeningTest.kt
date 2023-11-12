package com.durys.jakub.happeningservice.happening

import com.durys.jakub.happeningservice.happening.domain.*
import com.durys.jakub.happeningservice.happening.domain.Number
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.util.*

class HappeningTest {


    @Test
    fun shouldSuccessfullyCreateHappening() {
        assertDoesNotThrow { Happening(HappeningId(UUID.randomUUID()), Place("Warsaw"),
                Period(LocalDateTime.now(), LocalDateTime.now().plusDays(1))) }
    }

}