package com.durys.jakub.happeningservice.happening.infrastructure.`in`

import com.durys.jakub.happeningservice.happening.application.HappeningApplicationService
import com.durys.jakub.happeningservice.happening.domain.HappeningId
import com.durys.jakub.happeningservice.happening.domain.command.AppendInvitationPatternCommand
import com.durys.jakub.happeningservice.sharedkernel.invitation.content.InvitationQuestion
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("happenings")
internal class HappeningController(val happeningApplicationService: HappeningApplicationService) {

    @PostMapping("/{happeningId}/invitation-content")
    fun appendInvitationContent(@PathVariable happeningId: UUID, @RequestBody content: InvitationContentDTO) {
        happeningApplicationService.handle(
                AppendInvitationPatternCommand(HappeningId(happeningId), content.title, content.description, asQuestions(content.questions))
        )
    }


    private fun asQuestions(questions: Set<InvitationQuestionDTO>): Set<InvitationQuestion> {
        return questions
                .map { InvitationQuestion(it.question, it.required, it.type, it.availableOptions) }
                .toSet()
    }
}