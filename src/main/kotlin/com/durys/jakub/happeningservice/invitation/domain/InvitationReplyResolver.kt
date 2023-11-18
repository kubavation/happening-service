package com.durys.jakub.happeningservice.invitation.domain

import com.durys.jakub.happeningservice.sharedkernel.invitation.content.InvitationContent
import com.durys.jakub.happeningservice.sharedkernel.invitation.content.OptionType

internal class InvitationReplyResolver {

    fun resolve(answers: Set<InvitationAnswer>, invitationContent: InvitationContent): InvitationReply {

        val confirmationQuestion = invitationContent.options.find { it.type === OptionType.Confirmation }
                ?: throw RuntimeException("Confirmation not defined")

        val confirmation = answers.find { it.questionId == confirmationQuestion.id } ?.answer.toBoolean()

        answers.forEach { validate(it, invitationContent) }

        return InvitationReply(confirmation, answers)
    }

    private fun validate(answer: InvitationAnswer, invitationContent: InvitationContent) {
        invitationContent.options
                .find { it.id === answer.questionId } ?: throw RuntimeException("Question has not been answered")
    }
}