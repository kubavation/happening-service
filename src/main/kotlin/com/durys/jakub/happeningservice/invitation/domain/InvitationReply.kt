package com.durys.jakub.happeningservice.invitation.domain

import com.durys.jakub.happeningservice.sharedkernel.invitation.content.InvitationQuestionId

internal data class InvitationReply(val confirmation: Boolean, val answers: Set<InvitationAnswer>)

internal data class InvitationAnswer(val questionId: InvitationQuestionId, val answer: Boolean)