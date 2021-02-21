package cabanas.garcia.ismael.meetup.meetings.meeting

class MeetingCannotCanceledAfterStartException(val meetingId: MeetingId)
    : Exception("Meeting ${meetingId.value} cannot canceled because it has started.") {

}
