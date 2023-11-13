package com.durys.jakub.happeningservice.happening.infrastructure

import com.durys.jakub.happeningservice.happening.domain.Happening
import com.durys.jakub.happeningservice.happening.domain.HappeningId
import com.durys.jakub.happeningservice.happening.domain.HappeningRepository

class InMemoryHappeningRepository: HappeningRepository {

    private val db = HashMap<HappeningId, Happening>()

    override fun load(id: HappeningId): Happening {
        return db[id] ?: throw RuntimeException("Happening not found")
    }

    override fun save(happening: Happening) {
        db[happening.id()] = happening
    }
}