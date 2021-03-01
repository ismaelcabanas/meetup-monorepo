package cabanas.garcia.ismael.meetup.meetings.domain.meeting

class MeetingCannotChangedAfterHasStartedException()
    : Exception("Meeting cannot be changed after start.") {

}
