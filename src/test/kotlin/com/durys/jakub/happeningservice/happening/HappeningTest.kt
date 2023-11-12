package com.durys.jakub.happeningservice.happening

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.*

class HappeningTest {


    @Test
    fun shouldSuccessfullyCreateHappening() {
        assertDoesNotThrow { Happening(HappeningId(UUID.randomUUID())) }
    }

}