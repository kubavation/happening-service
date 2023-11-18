package com.durys.jakub.happeningservice.api

import com.durys.jakub.happeningservice.happening.infrastructure.query.HappeningQueryRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
internal class Happenings(private val happeningQueryRepository: HappeningQueryRepository) {

    @GetMapping
    fun findActiveHappenings(): List<HappeningDTO> = happeningQueryRepository.findActiveHappenings()

}