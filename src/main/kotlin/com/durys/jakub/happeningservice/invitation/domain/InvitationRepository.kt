package com.durys.jakub.happeningservice.invitation.domain

import com.durys.jakub.happeningservice.happening.domain.HappeningNumber

internal interface InvitationRepository {
    fun load(id: InvitationId): Invitation
    fun load(number: InvitationNumber): Invitation
    fun loadAll(happeningNumber: HappeningNumber): List<Invitation>
    fun save(invitation: Invitation): InvitationId
}