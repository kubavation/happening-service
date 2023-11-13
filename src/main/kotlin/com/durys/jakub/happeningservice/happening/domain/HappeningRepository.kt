package com.durys.jakub.happeningservice.happening.domain

internal interface HappeningRepository {
    fun load(id: HappeningId): Happening
    fun save(happening: Happening)
}