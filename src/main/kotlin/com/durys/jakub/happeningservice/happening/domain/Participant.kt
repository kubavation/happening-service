package com.durys.jakub.happeningservice.happening.domain

import java.util.UUID

internal class Participant(val id: ParticipantId, val name: String) {
}


internal class ParticipantId(val value: UUID)
