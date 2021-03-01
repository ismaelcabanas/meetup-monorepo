package cabanas.garcia.ismael.meetup.meetings.domain.meeting

interface MeetingRepository {
    abstract fun findById(meetingId: MeetingId): Meeting
}