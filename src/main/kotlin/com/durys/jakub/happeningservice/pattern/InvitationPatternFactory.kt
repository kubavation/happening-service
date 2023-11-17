package com.durys.jakub.happeningservice.pattern

import com.durys.jakub.happeningservice.sharedkernel.invitation.content.InvitationContent
import com.durys.jakub.happeningservice.sharedkernel.invitation.content.InvitationContentId
import com.durys.jakub.happeningservice.sharedkernel.invitation.content.InvitationQuestion
import com.durys.jakub.happeningservice.sharedkernel.invitation.content.InvitationQuestionId
import com.durys.jakub.happeningservice.sharedkernel.invitation.content.OptionType
import java.util.*

internal class InvitationPatternFactory {

    //todo db
    companion object {

        fun default(title: String): InvitationContent {
            return InvitationContent(InvitationContentId(UUID.randomUUID()), title, "Answer some questions",
                    setOf(
                        InvitationQuestion(InvitationQuestionId(UUID.randomUUID()),
                                "Are you going to attend?", true, OptionType.Confirmation))
            )
        }

    }
}