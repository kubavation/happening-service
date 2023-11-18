package com.durys.jakub.happeningservice.happening.infrastructure

import com.durys.jakub.happeningservice.happening.domain.Happening
import com.durys.jakub.happeningservice.happening.domain.HappeningId
import java.util.concurrent.ConcurrentHashMap

internal class InMemoryDataSource {
    val db = ConcurrentHashMap<HappeningId, Happening>()
}