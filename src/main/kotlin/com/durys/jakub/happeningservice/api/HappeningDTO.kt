package com.durys.jakub.happeningservice.api

import java.time.LocalDate
import java.util.UUID

class HappeningDTO(val id: UUID, val name: String, val at: LocalDate)