package com.durys.jakub.happeningservice.invitation.infrastructure

import com.durys.jakub.happeningservice.happening.domain.HappeningNumber
import com.durys.jakub.happeningservice.invitation.domain.Invitation
import com.durys.jakub.happeningservice.invitation.domain.InvitationId
import com.durys.jakub.happeningservice.invitation.domain.InvitationNumber
import com.durys.jakub.happeningservice.invitation.domain.InvitationRepository

internal class InMemoryInvitationRepository: InvitationRepository {

    private val db = HashMap<InvitationId, Invitation>()

    override fun load(id: InvitationId): Invitation {
        return db[id] ?: throw RuntimeException("Happening not found")
    }

    override fun load(number: InvitationNumber): Invitation {
        return db.values.find { it.number() == number.value } ?: throw RuntimeException("Happening not found")
    }

    override fun loadAll(happeningNumber: HappeningNumber): List<Invitation> {
        return db.filter { entry -> entry.key.happeningNumber === happeningNumber }.values.toList()
    }

    override fun save(invitation: Invitation): InvitationId {
        db[invitation.id()] = invitation
        return invitation.id()
    }
}