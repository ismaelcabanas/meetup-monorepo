package cabanas.garcia.ismael.meetup.domain.meetings.meeting

class MeetingAttendeeMustBeAMemberOfMeetingGroupException()
    : Exception("Meeting attendee must be a member of group.") {
}