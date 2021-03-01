package cabanas.garcia.ismael.meetup.meetings.domain.meeting

class MeetingAttendeeMustBeAMemberOfMeetingGroupException()
    : Exception("Meeting attendee must be a member of group.") {
}