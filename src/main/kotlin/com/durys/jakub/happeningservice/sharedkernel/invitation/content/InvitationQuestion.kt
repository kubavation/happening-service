package com.durys.jakub.happeningservice.sharedkernel.invitation.content

import java.util.*

internal data class InvitationQuestionId(val id: UUID)

internal data class InvitationQuestion(val id: InvitationQuestionId,
                                       val question: String, val required: Boolean,
                                       val type: OptionType, val availableOptions: Set<String> = emptySet()) {

    constructor(question: String, required: Boolean, type: OptionType)
            : this(InvitationQuestionId(UUID.randomUUID()), question, required, type)

}


internal enum class OptionType {
    Confirmation,
    Boolean,
    Date,
    Select,
    Text,
    Number,
    Other
}