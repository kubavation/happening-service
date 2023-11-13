package com.durys.jakub.happeningservice.events

import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
internal class EventsConfiguration {

    @Bean
    fun eventsPublisher(applicationEventPublisher: ApplicationEventPublisher): DomainEventPublisher {
        return SpringEventPublisher(applicationEventPublisher)
    }

}