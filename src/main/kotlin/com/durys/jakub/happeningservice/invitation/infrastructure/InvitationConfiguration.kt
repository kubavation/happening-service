package com.durys.jakub.happeningservice.invitation.infrastructure

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
internal class InvitationConfiguration {

    @Bean
    fun invitationRepository() = InMemoryInvitationRepository()

}