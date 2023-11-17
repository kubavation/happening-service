package com.durys.jakub.happeningservice.invitation.domain

import com.durys.jakub.happeningservice.sharedkernel.HappeningInvitationPattern
import com.durys.jakub.happeningservice.sharedkernel.InvitationQuestion
import com.durys.jakub.happeningservice.sharedkernel.InvitationQuestionId
import com.durys.jakub.happeningservice.sharedkernel.OptionType
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.*

class InvitationReplyResolverTest {


    @Test
    fun shouldResolveAnswerAsConfirmed() {

        val id1 = InvitationQuestionId(UUID.randomUUID())
        val id2 = InvitationQuestionId(UUID.randomUUID())

        val happeningInvitationPattern = HappeningInvitationPattern("title", "description",
                setOf(
                    InvitationQuestion(id1, "Question1", true, OptionType.Confirmation),
                    InvitationQuestion(id2, "Question2", true, OptionType.Other),
                ))

        val answers = setOf(
            InvitationAnswer(id1, true),
            InvitationAnswer(id2, false),
        )

        val result = InvitationReplyResolver().resolve(answers, happeningInvitationPattern)

        assertEquals(true, result.confirmation)
    }

    @Test
    fun shouldResolveAnswerAsUnconfirmed() {

        val id1 = InvitationQuestionId(UUID.randomUUID())
        val id2 = InvitationQuestionId(UUID.randomUUID())

        val happeningInvitationPattern = HappeningInvitationPattern("title", "description",
                setOf(
                        InvitationQuestion(id1, "Question1", true, OptionType.Confirmation),
                        InvitationQuestion(id2, "Question2", true, OptionType.Other),
                ))

        val answers = setOf(
                InvitationAnswer(id1, false),
                InvitationAnswer(id2, false),
        )

        val result = InvitationReplyResolver().resolve(answers, happeningInvitationPattern)

        assertEquals(false, result.confirmation)
    }

}