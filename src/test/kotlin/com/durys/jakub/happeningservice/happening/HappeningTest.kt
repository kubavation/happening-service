package com.durys.jakub.happeningservice.happening

import com.durys.jakub.happeningservice.happening.domain.*
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

    @Test
    fun shouldArchiveHappening() {

        val happening = Happening(HappeningId(UUID.randomUUID()), Place("Warsaw"),
                Period(LocalDateTime.now(), LocalDateTime.now().plusDays(1)))

        val result = happening.archive()

        assertEquals(happening.id(), result.happeningId)
        assertEquals(Happening.State.Archived, happening.state())
    }

}