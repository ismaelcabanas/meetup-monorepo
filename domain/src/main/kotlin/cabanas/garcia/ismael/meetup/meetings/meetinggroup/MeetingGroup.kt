package cabanas.garcia.ismael.meetup.meetings.meetinggroup

import cabanas.garcia.ismael.meetup.meetings.meeting.MeetingGroupLocation
import cabanas.garcia.ismael.meetup.meetings.member.MemberId
import cabanas.garcia.ismael.meetup.useraccess.userregistration.DomainEvent
import java.time.Instant

data class MeetingGroup(
    val id: MeetingGroupId,
    val creatorId: MemberId,
    val name: String,
    val description: String,
    val location: MeetingGroupLocation,
    val creationDate: Instant,
    val membersMeetingGroup: List<MemberMeetingGroup> = mutableListOf(MemberMeetingGroup(id, creatorId))
) {
    private var events = mutableListOf<DomainEvent>()

    fun isMemberMeetingGroup(memberId: MemberId): Boolean {
        val memberMeetingGroup = membersMeetingGroup.stream()
            .filter { memberMeetingGroup -> memberMeetingGroup.memberId == memberId }
            .findFirst()

        return memberMeetingGroup.isPresent
    }

    fun create(): MeetingGroup =
        this.also {
            registerDomainEvent(
                MeetingGroupCreated(
                    this.id.value,
                    this.creatorId.value,
                    this.name,
                    this.description,
                    this.location.country,
                    this.location.city,
                    this.creationDate
                )
            )
            registerDomainEvent(
                NewMeetingGroupMemberJoined(
                    this.id.value,
                    this.creatorId.value
                )
            )
        }

    fun events() = events

    private fun registerDomainEvent(domainEvent: DomainEvent) {
        events.add(domainEvent)
    }

}
