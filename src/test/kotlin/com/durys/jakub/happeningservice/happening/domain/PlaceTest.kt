package com.durys.jakub.happeningservice.happening.domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PlaceTest {

    @Test
    fun shouldCreatePlace() {
        assertDoesNotThrow { Place("Warsaw") }
    }

    @Test
    fun shouldThrowException() {
        val exception = assertThrows(RuntimeException::class.java, { Place("") })
        assertEquals("Place name cannot be empty", exception.message)
    }
}