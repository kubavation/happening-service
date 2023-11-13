package com.durys.jakub.happeningservice.events

import org.springframework.context.ApplicationEventPublisher

internal class SpringEventPublisher(private val applicationEventPublisher: ApplicationEventPublisher)
    : DomainEventPublisher {

    override fun publish(event: DomainEvent) {
        applicationEventPublisher.publishEvent(event)
    }
}