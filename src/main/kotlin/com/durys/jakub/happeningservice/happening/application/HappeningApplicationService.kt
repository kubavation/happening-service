package com.durys.jakub.happeningservice.happening.application

import com.durys.jakub.happeningservice.events.DomainEventPublisher
import com.durys.jakub.happeningservice.happening.domain.Happening
import com.durys.jakub.happeningservice.happening.domain.HappeningId
import com.durys.jakub.happeningservice.happening.domain.HappeningRepository
import com.durys.jakub.happeningservice.happening.domain.command.ArchiveHappeningCommand
import com.durys.jakub.happeningservice.happening.domain.command.CloseHappeningCommand
import com.durys.jakub.happeningservice.happening.domain.command.InitiateHappeningCommand
import com.durys.jakub.happeningservice.happening.domain.command.OpenHappeningCommand
import org.springframework.stereotype.Component

@Component
internal class HappeningApplicationService(private val happeningRepository: HappeningRepository,
        private val eventsPublisher: DomainEventPublisher) {


    fun handle(command: InitiateHappeningCommand): HappeningId {

        val happening = Happening.create(command.place, command.from, command.to, command.participantsIds)

        return happeningRepository.save(happening)
    }

    fun handle(command: ArchiveHappeningCommand) {

        val happening = happeningRepository.load(command.happeningId)

        happening.archive()
                .also { eventsPublisher.publish(it) }

    }

    fun handle(command: OpenHappeningCommand) {

        val happening = happeningRepository.load(command.happeningId)

        happening.sendInvitationsTo(command.participants, command.openTill)
                .also { eventsPublisher.publish(it) }

    }

    fun handle(command: CloseHappeningCommand) {

        val happening = happeningRepository.load(command.happeningId)

        happening.close(command.closedAt)
                .also { eventsPublisher.publish(it) }

    }


}