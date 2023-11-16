package com.durys.jakub.happeningservice.content

import java.util.UUID

internal data class InvitationContentId(val id: UUID)

internal data class InvitationContentPattern(val id: InvitationContentId, val title: String, val description: String, val options: Set<InvitationOption>)

internal data class InvitationOption(val question: String, val required: Boolean)