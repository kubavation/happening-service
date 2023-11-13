package com.durys.jakub.happeningservice.events

internal interface DomainEventPublisher {
    fun publish(event: DomainEvent)
}