package com.durys.jakub.happeningservice.happening.domain.command

import com.durys.jakub.happeningservice.happening.domain.HappeningId
import com.durys.jakub.happeningservice.sharedkernel.ParticipantId
import java.time.LocalDate

internal class OpenHappeningCommand(val happeningId: HappeningId, val participants: List<ParticipantId>, val openTill: LocalDate) {

}
