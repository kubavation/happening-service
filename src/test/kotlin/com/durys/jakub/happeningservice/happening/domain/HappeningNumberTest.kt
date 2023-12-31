package com.durys.jakub.happeningservice.happening.domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDate

class HappeningNumberTest {


    @Test
    fun shouldCreateHappeningNumber() {
        val place = Place("Warsaw")
        val period = Period(LocalDate.of(2023, 1, 1).atStartOfDay(),
                LocalDate.of(2023, 1, 3).atStartOfDay())

        val happeningNumber = HappeningNumber(period, place)

        assertEquals("Warsaw [2023-01-01-2023-01-03]", happeningNumber.value())
    }

}