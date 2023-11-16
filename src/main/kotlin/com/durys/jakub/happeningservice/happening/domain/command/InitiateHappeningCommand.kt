package com.durys.jakub.happeningservice.happening.domain.command

import java.time.LocalDateTime

internal data class InitiateHappeningCommand(val place: String, val from: LocalDateTime, val to: LocalDateTime)
