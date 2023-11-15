package com.durys.jakub.happeningservice.invitation.domain

internal interface InvitationRepository {
    fun load(id: InvitationId): Invitation
    fun load(number: InvitationNumber): Invitation
    fun save(invitation: Invitation): InvitationId
}