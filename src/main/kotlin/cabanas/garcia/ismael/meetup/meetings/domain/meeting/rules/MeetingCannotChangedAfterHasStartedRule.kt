package cabanas.garcia.ismael.meetup.meetings.domain.meeting.rules

import cabanas.garcia.ismael.meetup.meetings.domain.meeting.MeetingTerm
import cabanas.garcia.ismael.meetup.shared.domain.BusinessRule
import java.time.Instant

class MeetingCannotChangedAfterHasStartedRule(
    private val meetingTerm: MeetingTerm,
    private val date: Instant
) : BusinessRule {

    override fun isBroken(): Boolean = meetingTerm.isAfterStart(date)

    override fun message(): String = "Meeting cannot be changed after start."
}