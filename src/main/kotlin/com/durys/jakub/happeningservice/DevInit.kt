package com.durys.jakub.happeningservice

import com.durys.jakub.happeningservice.happening.domain.*
import com.durys.jakub.happeningservice.happening.domain.Happening
import com.durys.jakub.happeningservice.happening.domain.HappeningId
import com.durys.jakub.happeningservice.happening.domain.HappeningRepository
import com.durys.jakub.happeningservice.happening.domain.Period
import com.durys.jakub.happeningservice.happening.domain.Place
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDateTime
import java.util.*

@Configuration
internal class DevInit {

    @Bean
    internal fun commandLineRunner(happeningRepository: HappeningRepository): CommandLineRunner {
        return CommandLineRunner { _ ->
            setOf(
                    Happening(HappeningId(UUID.randomUUID()), Place("Warsaw"),
                            Period(LocalDateTime.now(), LocalDateTime.now().plusDays(1)), Happening.State.Open),
                    Happening(HappeningId(UUID.randomUUID()), Place("Krakow"),
                            Period(LocalDateTime.now(), LocalDateTime.now().plusDays(1)), Happening.State.Open),
                    Happening(HappeningId(UUID.randomUUID()), Place("Wroclaw"),
                            Period(LocalDateTime.now(), LocalDateTime.now().plusDays(1)), Happening.State.New))
                    .forEach { happeningRepository.save(it) }
        }
    }

}