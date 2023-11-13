package com.durys.jakub.happeningservice.happening.domain

data class Place(val name: String) {

    init {
        Validator.test(name)
    }

    internal object Validator {
        fun test(place: String) {
            if (place.isEmpty()) {
                throw RuntimeException("Place name cannot be empty")
            }
        }
    }
}