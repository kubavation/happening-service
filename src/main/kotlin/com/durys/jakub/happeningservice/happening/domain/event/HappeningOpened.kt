package com.durys.jakub.happeningservice.happening.domain.event

import com.durys.jakub.happeningservice.events.DomainEvent
import com.durys.jakub.happeningservice.happening.domain.HappeningNumber
import com.durys.jakub.happeningservice.sharedkernel.invitation.content.InvitationContent
import com.durys.jakub.happeningservice.sharedkernel.ParticipantId
import java.time.Instant
import java.time.LocalDate
import java.util.*

internal class HappeningOpened(id: UUID, at: Instant, val happeningNumber: HappeningNumber,
                               val participants: List<ParticipantId>, val validTo: LocalDate,
                               val invitationPattern: InvitationContent): DomainEvent(id, at)