package cabanas.garcia.ismael.meetup.meetings.meeting

import java.time.Instant

data class EnrolmentTerm(val startDate: Instant?, val endDate: Instant?) {
    fun isInTerm(date: Instant): Boolean = date.isAfter(startDate) && date.isBefore(endDate)
}