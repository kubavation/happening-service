package com.durys.jakub.happeningservice.invitation.domain.command

import com.durys.jakub.happeningservice.happening.domain.HappeningNumber
import com.durys.jakub.happeningservice.sharedkernel.ParticipantId

internal data class ReplyToInvitationCommand(val participantId: ParticipantId,
                                             val happeningNumber: HappeningNumber, val confirmation: Boolean) {
}