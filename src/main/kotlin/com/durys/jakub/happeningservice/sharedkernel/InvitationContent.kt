package com.durys.jakub.happeningservice.sharedkernel

import java.util.UUID

internal data class InvitationPatternId(val id: UUID)

internal data class HappeningInvitationPattern(val id: InvitationPatternId, val title: String, val description: String, val options: Set<InvitationOption>)

internal data class InvitationOption(val id: UUID, val question: String, val required: Boolean, val type: OptionType)

internal enum class OptionType {
    Confirmation
}