package cabanas.garcia.ismael.meetup.shared.infrastructure.service

import java.time.Instant

fun interface DateProvider {
    fun now(): Instant
}