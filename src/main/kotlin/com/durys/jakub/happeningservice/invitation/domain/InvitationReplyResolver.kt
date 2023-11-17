package com.durys.jakub.happeningservice.invitation.domain

import com.durys.jakub.happeningservice.sharedkernel.HappeningInvitationPattern
import com.durys.jakub.happeningservice.sharedkernel.OptionType

internal class InvitationReplyResolver {

    fun resolve(answers: Set<InvitationAnswer>, invitationContent: HappeningInvitationPattern): InvitationReply {

        val confirmationQuestion = invitationContent.options.find { it.type === OptionType.Confirmation }!!

        val confirmation = answers.find { it.questionId == confirmationQuestion.id } ?.answer ?: false

        return InvitationReply(confirmation, answers)
    }
}