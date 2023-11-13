package com.durys.jakub.happeningservice.invitation.application.event.handler

import com.durys.jakub.happeningservice.happening.domain.event.HappeningOpened
import com.durys.jakub.happeningservice.invitation.domain.Invitation
import com.durys.jakub.happeningservice.invitation.infrastructure.InMemoryInvitationRepository
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
internal class HappeningOpenedEventHandler(private val invitationRepository: InMemoryInvitationRepository) {


    @EventListener
    fun on(event: HappeningOpened) {

        val happeningNumber = event.happeningNumber
        val validTo = event.validTo

        event.participants
                .map { Invitation.create(it, happeningNumber, validTo) }
                .forEach { invitationRepository.save(it) }

    }

}