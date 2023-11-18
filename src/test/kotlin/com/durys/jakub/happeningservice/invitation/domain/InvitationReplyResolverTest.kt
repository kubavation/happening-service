package com.durys.jakub.happeningservice.invitation.domain

import com.durys.jakub.happeningservice.sharedkernel.invitation.content.InvitationContent
import com.durys.jakub.happeningservice.sharedkernel.invitation.content.InvitationQuestion
import com.durys.jakub.happeningservice.sharedkernel.invitation.content.InvitationQuestionId
import com.durys.jakub.happeningservice.sharedkernel.invitation.content.OptionType
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.*

class InvitationReplyResolverTest {


    @Test
    fun shouldResolveAnswerAsConfirmed() {

        val id1 = InvitationQuestionId(UUID.randomUUID())
        val id2 = InvitationQuestionId(UUID.randomUUID())

        val invitationContent = InvitationContent("title", "description",
                setOf(
                    InvitationQuestion(id1, "Question1", true, OptionType.Confirmation),
                    InvitationQuestion(id2, "Question2", true, OptionType.Other),
                ))

        val answers = setOf(
            InvitationAnswer(id1, "true"),
            InvitationAnswer(id2, "false"),
        )

        val result = InvitationReplyResolver().resolve(answers, invitationContent)

        assertEquals(true, result.confirmation)
    }

    @Test
    fun shouldResolveAnswerAsUnconfirmed() {

        val id1 = InvitationQuestionId(UUID.randomUUID())
        val id2 = InvitationQuestionId(UUID.randomUUID())

        val invitationContent = InvitationContent("title", "description",
                setOf(
                        InvitationQuestion(id1, "Question1", true, OptionType.Confirmation),
                        InvitationQuestion(id2, "Question2", true, OptionType.Other),
                ))

        val answers = setOf(
                InvitationAnswer(id1, "false"),
                InvitationAnswer(id2, "false"),
        )

        val result = InvitationReplyResolver().resolve(answers, invitationContent)

        assertEquals(false, result.confirmation)
    }

}