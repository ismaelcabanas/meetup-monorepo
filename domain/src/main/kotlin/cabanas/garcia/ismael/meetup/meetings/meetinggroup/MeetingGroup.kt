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
    val creationDate: Instant
) {
    private var membersMeetingGroup = mutableListOf<MemberMeetingGroup>()
    private var events = mutableListOf<DomainEvent>()

    init {
        membersMeetingGroup.add(MemberMeetingGroup(id, creatorId))
    }

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

    fun update(newName: String, newDescription: String, newLocation: MeetingGroupLocation): MeetingGroup =
        MeetingGroup(
            this.id,
            this.creatorId,
            newName,
            newDescription,
            newLocation,
            this.creationDate
        ).also {
            it.membersMeetingGroup = this.membersMeetingGroup
            it.events = this.events
            it.registerDomainEvent(
                MeetingGroupUpdated(
                    this.id.value,
                    newName,
                    newDescription,
                    newLocation.country,
                    newLocation.city
                )
            )
        }

    fun join(newMember: MemberId): MeetingGroup {
        if (memberAlreadyExist(newMember)) {
            throw MemberHasAlreadyJoinedException(newMember, this.id)
        }

        addNewMember(newMember)

        registerDomainEvent(
            NewMeetingGroupMemberJoined(
                this.id.value,
                newMember.value
            )
        )

        return MeetingGroup(
            this.id,
            this.creatorId,
            this.name,
            this.description,
            this.location,
            this.creationDate
        ).also {
            it.membersMeetingGroup = this.membersMeetingGroup
            it.events = this.events
        }
    }

    fun membersGroup(): List<MemberMeetingGroup> = this.membersMeetingGroup.toList()

    fun events() = events.toList()

    private fun addNewMember(newMember: MemberId) {
        this.membersMeetingGroup.add(MemberMeetingGroup(id, newMember))
    }

    private fun memberAlreadyExist(member: MemberId): Boolean =
        this.membersMeetingGroup.contains(MemberMeetingGroup(id, member))

    private fun registerDomainEvent(domainEvent: DomainEvent) {
        events.add(domainEvent)
    }
}
