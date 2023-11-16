package com.durys.jakub.happeningservice.happening.domain

import com.durys.jakub.happeningservice.content.InvitationOption

internal data class HappeningInvitationPattern(val title: String, val description: String, val options: Set<InvitationOption>)

internal data class InvitationOption(val question: String, val required: Boolean)