package com.durys.jakub.happeningservice.sharedkernel

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class InvitationContentTest {


    @Test
    fun shouldCreateHappeningInvitationPattern() {
        assertDoesNotThrow {  InvitationContent("title", "description",
                setOf(InvitationQuestion("Question", true, OptionType.Confirmation))) }
    }

    @Test
    fun shouldThrowException_whenConfirmationQuestionNotDefined() {
        val exception = assertThrows(RuntimeException::class.java) {
            InvitationContent("title", "description",
                    setOf(InvitationQuestion("Question", true, OptionType.Other))) }

        assertEquals("Confirmation question not provided", exception.message)
    }

    @Test
    fun shouldThrowException_whenConfirmationQuestionDefinedMoreThanOnce() {
        val exception = assertThrows(RuntimeException::class.java) {
            InvitationContent("title", "description",
                    setOf(
                        InvitationQuestion("Question1", true, OptionType.Confirmation),
                        InvitationQuestion("Question2", true, OptionType.Confirmation)
                    )) }

        assertEquals("It has to be only one confirmation question", exception.message)
    }
}