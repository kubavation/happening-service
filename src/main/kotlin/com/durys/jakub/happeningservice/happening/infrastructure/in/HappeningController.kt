package com.durys.jakub.happeningservice.happening.infrastructure.`in`

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("happenings")
internal class HappeningController {

    @PostMapping("/{happeningId}/invitation-content")
    fun appendInvitationContent(@PathVariable happeningId: UUID, @RequestBody content: InvitationContentDTO) {
        //todo
    }
}