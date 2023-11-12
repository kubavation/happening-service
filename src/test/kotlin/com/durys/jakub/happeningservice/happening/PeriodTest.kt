package com.durys.jakub.happeningservice.happening

import com.durys.jakub.happeningservice.happening.domain.Period
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class PeriodTest {


    @Test
    fun shouldCreatePeriod() {
        assertDoesNotThrow { Period(LocalDateTime.now(), LocalDateTime.now().plusDays(1)) }
    }

    @Test
    fun shouldThrowExceptionWhenDatesAreInvalid() {
        val exception = assertThrows(RuntimeException::class.java, { Period(LocalDateTime.now(), LocalDateTime.now().minusDays(2)) })
        assertEquals("Date from cannot be later than date to", exception.message)

    }
}