package com.durys.jakub.happeningservice.happening.domain

interface HappeningRepository {
    fun load(id: HappeningId): Happening
    fun save(happening: Happening)
}