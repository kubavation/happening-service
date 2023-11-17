package com.durys.jakub.happeningservice.pattern

import com.durys.jakub.happeningservice.sharedkernel.HappeningInvitationPattern
import com.durys.jakub.happeningservice.sharedkernel.InvitationOption
import com.durys.jakub.happeningservice.sharedkernel.InvitationPatternId
import com.durys.jakub.happeningservice.sharedkernel.OptionType
import java.util.UUID

internal class InvitationPatternFactory {

    //todo db
    companion object {

        fun default(title: String): HappeningInvitationPattern {
            return HappeningInvitationPattern(InvitationPatternId(UUID.randomUUID()), title, "Answer some questions",
                    setOf(
                        InvitationOption(UUID.randomUUID(), "Are you going to attend?", true, OptionType.Confirmation))
            )
        }

    }
}