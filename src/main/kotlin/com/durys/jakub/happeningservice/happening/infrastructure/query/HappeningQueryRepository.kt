package com.durys.jakub.happeningservice.happening.infrastructure.query

import com.durys.jakub.happeningservice.api.HappeningDTO
import com.durys.jakub.happeningservice.happening.domain.Happening
import com.durys.jakub.happeningservice.happening.infrastructure.InMemoryDataSource

internal class HappeningQueryRepository(private val dataSource: InMemoryDataSource) {

    fun findActiveHappenings(): List<HappeningDTO> {
        return dataSource.db
                .values
                .filter { it.state() == Happening.State.Open }
                .map { HappeningDTO(it.id().value, it.place().name, it.at().from.toLocalDate()) }
    }

}