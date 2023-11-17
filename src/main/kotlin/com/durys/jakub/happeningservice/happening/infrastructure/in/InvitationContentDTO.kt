package com.durys.jakub.happeningservice.happening.infrastructure.`in`

import com.durys.jakub.happeningservice.sharedkernel.invitation.content.OptionType

internal data class InvitationContentDTO(val title: String, val description: String, val questions: Set<InvitationQuestionDTO>) {
}

internal data class InvitationQuestionDTO(val question: String, val required: Boolean,
                                          val type: OptionType, val availableOptions: Set<String> = emptySet())