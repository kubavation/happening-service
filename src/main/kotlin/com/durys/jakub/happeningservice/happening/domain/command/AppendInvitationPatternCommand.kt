package com.durys.jakub.happeningservice.happening.domain.command

import com.durys.jakub.happeningservice.content.InvitationOption
import com.durys.jakub.happeningservice.happening.domain.HappeningId

internal data class AppendInvitationPatternCommand(val happeningId: HappeningId, val title: String,
                                                   val description: String, val options: Set<InvitationOption>)