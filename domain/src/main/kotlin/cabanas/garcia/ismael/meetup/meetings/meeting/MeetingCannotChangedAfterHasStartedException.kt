package cabanas.garcia.ismael.meetup.meetings.meeting

class MeetingCannotChangedAfterHasStartedException(val meetingId: MeetingId)
    : Exception("Meeting ${meetingId.value} cannot be changed after it has started.") {

}
