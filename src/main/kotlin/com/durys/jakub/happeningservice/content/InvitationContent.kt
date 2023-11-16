package com.durys.jakub.happeningservice.content

internal data class InvitationContentPattern(val title: String, val description: String, val options: Set<InvitationOption>)

internal data class InvitationOption(val question: String, val required: Boolean)