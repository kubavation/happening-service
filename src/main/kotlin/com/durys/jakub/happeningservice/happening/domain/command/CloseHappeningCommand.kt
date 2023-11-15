package com.durys.jakub.happeningservice.happening.domain.command

import com.durys.jakub.happeningservice.happening.domain.HappeningId
import com.durys.jakub.happeningservice.happening.domain.HappeningNumber
import java.time.LocalDate

internal class CloseHappeningCommand(val happeningId: HappeningId, val closedAt: LocalDate)
