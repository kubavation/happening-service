package com.durys.jakub.happeningservice.happening.domain

class Happening(val id: HappeningId, val place: Place,
                val period: Period, val participants: MutableList<Participant> = mutableListOf()) {

    val number = Number(period, place)


    fun invite(participants: List<Participant>) = this.participants.addAll(participants)
}