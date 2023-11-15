package com.durys.jakub.happeningservice.happening.domain.event

import com.durys.jakub.happeningservice.events.DomainEvent
import com.durys.jakub.happeningservice.happening.domain.HappeningId
import com.durys.jakub.happeningservice.happening.domain.HappeningNumber
import com.durys.jakub.happeningservice.sharedkernel.ParticipantId
import java.time.Instant
import java.time.LocalDate
import java.util.*

internal class HappeningClosed(id: UUID, at: Instant, val happeningNumber: HappeningNumber,
                               val closedAt: LocalDate): DomainEvent(id, at)