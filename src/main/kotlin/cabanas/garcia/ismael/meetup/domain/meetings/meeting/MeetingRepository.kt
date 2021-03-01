package cabanas.garcia.ismael.meetup.domain.meetings.meeting

interface MeetingRepository {
    abstract fun findById(meetingId: MeetingId): Meeting
}