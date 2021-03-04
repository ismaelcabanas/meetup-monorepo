package cabanas.garcia.ismael.meetup.meetings.domain.meeting.rules

import cabanas.garcia.ismael.meetup.meetings.domain.meetinggroup.MeetingGroup
import cabanas.garcia.ismael.meetup.meetings.domain.member.MemberId
import cabanas.garcia.ismael.meetup.shared.domain.BusinessRule

class MemberOnWaitListMustBeMemberOfMeetingGroupRule(
    private val meetingGroup: MeetingGroup,
    private val memberId: MemberId
) : BusinessRule {

    override fun isBroken(): Boolean = !meetingGroup.isMemberMeetingGroup(memberId)

    override fun message(): String = "Member on waitlist must be a member of meeting group."
}