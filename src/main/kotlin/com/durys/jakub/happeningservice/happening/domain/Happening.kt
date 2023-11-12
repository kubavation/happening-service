package com.durys.jakub.happeningservice.happening.domain

class Happening(val id: HappeningId, val number: Number, val place: Place,
                val period: Period, val participants: MutableList<Participant> = mutableListOf()) {



    fun invite(participants: List<Participant>) = this.participants.addAll(participants)
}