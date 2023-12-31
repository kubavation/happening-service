package com.durys.jakub.happeningservice.sharedkernel.invitation.content

import java.util.UUID

internal data class InvitationContentId(val id: UUID)

internal data class InvitationContent(val id: InvitationContentId, val title: String, val description: String, val options: Set<InvitationQuestion>) {

    init {

        val confirmationOptions = options.filter { it.type == OptionType.Confirmation }

        if (confirmationOptions.isEmpty()) {
            throw RuntimeException("Confirmation question not provided")
        }

        if (confirmationOptions.size > 1) {
            throw RuntimeException("It has to be only one confirmation question")
        }

    }

    constructor(title: String, description: String, options: Set<InvitationQuestion>)
            : this(InvitationContentId(UUID.randomUUID()), title, description, options)

}
