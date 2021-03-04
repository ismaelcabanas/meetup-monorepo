package cabanas.garcia.ismael.meetup.meetings.domain.meeting.rules

import cabanas.garcia.ismael.meetup.meetings.domain.meeting.EnrolmentTerm
import cabanas.garcia.ismael.meetup.shared.domain.BusinessRule
import java.time.Instant

class MeetingAttendeeMustBeAddedInEnrolmentTermRule(
    private val enrolmentTerm: EnrolmentTerm,
    private val enrolmentDate: Instant
) : BusinessRule {
    constructor(enrolmentTerm: EnrolmentTerm) : this(enrolmentTerm, Instant.now())

    override fun isBroken(): Boolean = !enrolmentTerm.isInTerm(enrolmentDate)

    override fun message(): String = "Attendee can be added only in enrolment term."
}