package com.durys.jakub.happeningservice.happening.domain.event

import com.durys.jakub.happeningservice.events.DomainEvent
import com.durys.jakub.happeningservice.happening.domain.HappeningId
import java.time.Instant
import java.util.*

internal class HappeningArchived(id: UUID, at: Instant, val happeningId: HappeningId): DomainEvent(id, at)