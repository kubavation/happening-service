package com.durys.jakub.happeningservice.happening.infrastructure

import com.durys.jakub.happeningservice.happening.domain.HappeningRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class HappeningConfiguration {

    @Bean
    fun happeningRepository(): HappeningRepository = InMemoryHappeningRepository()
}