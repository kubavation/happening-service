package com.durys.jakub.happeningservice.sharedkernel

import java.util.UUID

internal data class InvitationPatternId(val id: UUID)

internal data class HappeningInvitationPattern(val id: InvitationPatternId, val title: String, val description: String, val options: Set<InvitationQuestion>) {

    constructor(title: String, description: String, options: Set<InvitationQuestion>)
            : this(InvitationPatternId(UUID.randomUUID()), title, description, options)

}

internal data class InvitationQuestionId(val id: UUID)

internal data class InvitationQuestion(val id: InvitationQuestionId, val question: String, val required: Boolean, val type: OptionType) {

    constructor(question: String, required: Boolean, type: OptionType)
            : this(InvitationQuestionId(UUID.randomUUID()), question, required, type)

}

internal enum class OptionType {
    Confirmation
}