package com.durys.jakub.happeningservice.invitation.domain.command

import com.durys.jakub.happeningservice.invitation.domain.InvitationAnswer
import com.durys.jakub.happeningservice.invitation.domain.InvitationNumber

internal data class ReplyToInvitationCommand(val number: InvitationNumber, val answers: Set<InvitationAnswer>)