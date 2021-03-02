package cabanas.garcia.ismael.meetup.meetings.domain.meeting

interface MeetingRepository {
    fun findById(meetingId: MeetingId): Meeting
    fun save(meeting: Meeting)
}