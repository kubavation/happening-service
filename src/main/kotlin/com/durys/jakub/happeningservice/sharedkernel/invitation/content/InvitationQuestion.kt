package com.durys.jakub.happeningservice.sharedkernel.invitation.content

import java.util.*

internal data class InvitationQuestionId(val id: UUID)

internal data class InvitationQuestion(val id: InvitationQuestionId,
                                       val question: String, val required: Boolean,
                                       val type: OptionType, val availableOptions: Set<String> = emptySet()) {

    init {
        if (type == OptionType.Select && availableOptions.isEmpty()) {
            throw RuntimeException("Options not provided")
        }
    }

    constructor(question: String, required: Boolean, type: OptionType, availableOptions: Set<String> = emptySet())
            : this(InvitationQuestionId(UUID.randomUUID()), question, required, type, availableOptions)

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