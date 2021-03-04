package cabanas.garcia.ismael.meetup.meetings.domain.meeting

import cabanas.garcia.ismael.meetup.shared.domain.DomainException

class MemberOnWaitListMustBeMemberOfMeetingGroupException
    : DomainException("Member on waitlist must be a member of meeting group.")