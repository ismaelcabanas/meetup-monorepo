package cabanas.garcia.ismael.meetup.meetings.application.signoffmemberfromwaitlist

data class SignOffMemberFromWaitListCommand(
    val meetingId: String,
    val memberId: String
)
