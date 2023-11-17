package com.durys.jakub.happeningservice.pattern

import com.durys.jakub.happeningservice.sharedkernel.*
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