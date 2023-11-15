package com.durys.jakub.happeningservice.invitation.application.event.handler

import com.durys.jakub.happeningservice.happening.domain.event.HappeningClosed
import com.durys.jakub.happeningservice.happening.domain.event.HappeningOpened
import com.durys.jakub.happeningservice.invitation.domain.Invitation
import com.durys.jakub.happeningservice.invitation.infrastructure.InMemoryInvitationRepository
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
internal class HappeningClosedEventHandler(private val invitationRepository: InMemoryInvitationRepository) {


    @EventListener
    fun on(event: HappeningClosed) {

        invitationRepository.loadAll(event.happeningNumber)
                .forEach {
                    it.close(event.closedAt)
                    invitationRepository.save(it)
                }

    }

}