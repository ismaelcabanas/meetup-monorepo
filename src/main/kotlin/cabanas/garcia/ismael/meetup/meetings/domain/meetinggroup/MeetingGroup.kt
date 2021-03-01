package cabanas.garcia.ismael.meetup.meetings.domain.meetinggroup

import cabanas.garcia.ismael.meetup.meetings.domain.meeting.MeetingGroupLocation
import cabanas.garcia.ismael.meetup.meetings.domain.member.MemberId
import cabanas.garcia.ismael.meetup.shared.DomainEvent
import java.time.Instant

data class MeetingGroup(
    val id: MeetingGroupId,
    val creatorId: MemberId,
    val name: String,
    val description: String,
    val location: MeetingGroupLocation,
    val creationDate: Instant
) {
    private var membersMeetingGroup: List<MemberMeetingGroup> = mutableListOf(MemberMeetingGroup(id, creatorId))
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
        if (isMemberMeetingGroup(newMember)) {
            throw MemberHasAlreadyJoinedException(newMember, this.id)
        }

        val newMemberMeetingGroup = addNewMember(newMember)

        registerDomainEvent(
            NewMeetingGroupMemberJoined(
                id.value,
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
            it.membersMeetingGroup = newMemberMeetingGroup
            it.events = this.events
        }
    }

    fun leave(memberId: MemberId): MeetingGroup {
        if (isCreator(memberId)) {
            throw CreatorMemberCannotLeaveGroupException(memberId, id)
        }
        if (!isMemberMeetingGroup(memberId)) {
            throw MemberCannotLeaveGroupException(memberId, id)
        }

        val newMemberMeetingGroup = removeMember(memberId)

        registerDomainEvent(
            MeetingGroupMemberLeftGroup(
                id.value,
                memberId.value
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
            it.membersMeetingGroup = newMemberMeetingGroup
            it.events = this.events
        }
    }

    fun membersGroup(): List<MemberMeetingGroup> = this.membersMeetingGroup.toList()

    fun events() = events.toList()

    private fun isCreator(memberId: MemberId): Boolean = memberId == creatorId

    private fun addNewMember(newMember: MemberId) : List<MemberMeetingGroup> {
        return this.membersMeetingGroup.plus(MemberMeetingGroup(id, newMember))
    }

    private fun removeMember(memberId: MemberId): List<MemberMeetingGroup> =
        membersMeetingGroup.filter { it != MemberMeetingGroup(id, memberId) }

    private fun registerDomainEvent(domainEvent: DomainEvent) {
        events.add(domainEvent)
    }
}
