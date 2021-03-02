package cabanas.garcia.ismael.meetup.meetings.domain.meetinggroup

interface MeetingGroupRepository {
    fun findBy(id: MeetingGroupId): MeetingGroup
}