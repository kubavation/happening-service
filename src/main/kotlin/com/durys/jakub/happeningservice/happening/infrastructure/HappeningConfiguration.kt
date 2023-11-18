package com.durys.jakub.happeningservice.happening.infrastructure

import com.durys.jakub.happeningservice.happening.domain.HappeningRepository
import com.durys.jakub.happeningservice.happening.infrastructure.query.HappeningQueryRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
internal class HappeningConfiguration {

    @Bean
    fun dataSource() = InMemoryDataSource()

    @Bean
    fun happeningRepository(dataSource: InMemoryDataSource): HappeningRepository = InMemoryHappeningRepository(dataSource)

    @Bean
    fun happeningQueryRepository(dataSource: InMemoryDataSource) = HappeningQueryRepository(dataSource)
}