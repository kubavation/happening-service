package com.durys.jakub.happeningservice.happening.application

import com.durys.jakub.happeningservice.happening.domain.Happening
import com.durys.jakub.happeningservice.happening.domain.HappeningId
import com.durys.jakub.happeningservice.happening.domain.HappeningRepository
import com.durys.jakub.happeningservice.happening.domain.command.ArchiveHappeningCommand
import com.durys.jakub.happeningservice.happening.domain.command.InitiateHappeningCommand
import org.springframework.stereotype.Component

@Component
internal class HappeningApplicationService(private val happeningRepository: HappeningRepository) {


    fun handle(command: InitiateHappeningCommand): HappeningId {

        val happening = Happening.create(command.place, command.from, command.to, command.participantsIds)

        return happeningRepository.save(happening)
    }

    fun handle(command: ArchiveHappeningCommand) {

        val happening = happeningRepository.load(command.happeningId)

        happening.archive()
    }


}