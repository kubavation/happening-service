package com.durys.jakub.happeningservice.events

import java.time.Instant
import java.util.UUID

internal open class DomainEvent(val id: UUID, at: Instant) {
}