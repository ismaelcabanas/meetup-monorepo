package cabanas.garcia.ismael.meetup.meetings.domain.meeting.rules

import cabanas.garcia.ismael.meetup.meetings.domain.meeting.MeetingWaitListMember
import cabanas.garcia.ismael.meetup.meetings.domain.member.MemberId
import cabanas.garcia.ismael.meetup.shared.domain.BusinessRule

class MemberCannotBeMoreThanOnceOnMeetingWaitListRule(
    private val waitListMembers: List<MeetingWaitListMember>,
    private val memberId: MemberId
) : BusinessRule {
    override fun isBroken(): Boolean =
        waitListMembers.firstOrNull { it.memberId == memberId } != null

    override fun message(): String = "Member already exist on wait list."
}