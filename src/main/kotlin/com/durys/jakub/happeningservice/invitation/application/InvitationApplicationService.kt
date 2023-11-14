package com.durys.jakub.happeningservice.invitation.application

import com.durys.jakub.happeningservice.invitation.domain.InvitationId
import com.durys.jakub.happeningservice.invitation.domain.InvitationRepository
import com.durys.jakub.happeningservice.invitation.domain.command.ReplyToInvitationCommand
import org.springframework.stereotype.Component

@Component
internal class InvitationApplicationService(private val invitationRepository: InvitationRepository) {


    fun handle(command: ReplyToInvitationCommand) {

        val invitation = invitationRepository.load(InvitationId(command.participantId, command.happeningNumber))

        invitation.reply(command.confirmation)
    }

}