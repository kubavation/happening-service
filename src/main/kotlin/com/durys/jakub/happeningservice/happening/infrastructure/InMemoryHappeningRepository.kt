package com.durys.jakub.happeningservice.happening.infrastructure

import com.durys.jakub.happeningservice.happening.domain.Happening
import com.durys.jakub.happeningservice.happening.domain.HappeningId
import com.durys.jakub.happeningservice.happening.domain.HappeningRepository

internal class InMemoryHappeningRepository(private val dataSource: InMemoryDataSource): HappeningRepository {

    override fun load(id: HappeningId): Happening {
        return dataSource.db[id] ?: throw RuntimeException("Happening not found")
    }

    override fun save(happening: Happening): HappeningId {
        dataSource.db[happening.id()] = happening
        return happening.id()
    }
}