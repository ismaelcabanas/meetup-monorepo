package cabanas.garcia.ismael.meetup.meetings.domain.meeting

import cabanas.garcia.ismael.meetup.meetings.domain.member.MemberId

data class OnlyMeetingAttendeesCanBeRemovedException(
    val meetingId: MeetingId,
    val attendeeId: MemberId)
    : Exception("Member '${attendeeId.value}' is not an attendee of meeting '${meetingId.value}'.")
