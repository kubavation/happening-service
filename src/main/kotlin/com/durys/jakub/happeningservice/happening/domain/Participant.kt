package com.durys.jakub.happeningservice.happening.domain

import java.util.UUID

class Participant(val id: ParticipantId, val name: String) {
}


class ParticipantId(val value: UUID)
