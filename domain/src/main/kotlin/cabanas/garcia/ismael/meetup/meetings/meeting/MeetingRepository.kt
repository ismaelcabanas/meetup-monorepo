package cabanas.garcia.ismael.meetup.meetings.meeting

interface MeetingRepository {
    abstract fun findById(meetingId: MeetingId): Meeting
}