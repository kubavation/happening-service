package com.durys.jakub.happeningservice.invitation.domain

import com.durys.jakub.happeningservice.happening.domain.HappeningNumber
import com.durys.jakub.happeningservice.sharedkernel.ParticipantId

internal class Invitation(val id: InvitationId) {

    fun id() = id
}

internal data class InvitationId(val participantId: ParticipantId, val happeningNumber: HappeningNumber)