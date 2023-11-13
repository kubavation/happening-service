package com.durys.jakub.happeningservice.happening.domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDate

class HappeningFactoryTest {

    @Test
    fun shouldCreateHappeningInstance() {

        val place = "Warsaw"
        val from = LocalDate.of(2023, 1, 1).atStartOfDay()
        val to = LocalDate.of(2023, 1, 3).atStartOfDay()

        val happening = Happening.create(place, from, to, mutableListOf())

        assertEquals(Happening.State.New, happening.state)
    }

}