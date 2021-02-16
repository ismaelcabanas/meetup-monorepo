package cabanas.garcia.ismael.meetup.meetings.meetinggroup

import cabanas.garcia.ismael.meetup.meetings.meeting.MeetingGroupLocation
import cabanas.garcia.ismael.meetup.meetings.member.MemberId
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class MeetingGroupShould {
    @Test
    fun `update meeting group successfully`() {
        val meetingGroup = MeetingGroupMother.create()

        val meetingGroupUpdated = meetingGroup.update(NEW_NAME, NEW_DESCRIPTION, NEW_LOCATION)

        meetingGroupUpdated.events() shouldContain
                MeetingGroupUpdated(
                    meetingGroup.id.value,
                    NEW_NAME,
                    NEW_DESCRIPTION,
                    NEW_LOCATION.country,
                    NEW_LOCATION.city
                )
        meetingGroupUpdated shouldBe MeetingGroup(
            meetingGroup.id,
            meetingGroup.creatorId,
            NEW_NAME,
            NEW_DESCRIPTION,
            NEW_LOCATION,
            meetingGroup.creationDate
        )
        meetingGroupUpdated.membersGroup() shouldContainExactly mutableListOf(
            MemberMeetingGroup(meetingGroup.id, meetingGroup.creatorId)
        )
    }

    @Test
    fun `join member to group when member has not joined yet`() {
        val meetingGroup = MeetingGroupMother.create()
        val newMember = MemberId(NEW_MEMBER_ID)

        val meetingGroupWithNewMember = meetingGroup.join(newMember)

        meetingGroupWithNewMember.events() shouldContain
                NewMeetingGroupMemberJoined(
                    meetingGroup.id.value,
                    NEW_MEMBER_ID
                )
        meetingGroupWithNewMember shouldBe MeetingGroup(
            meetingGroup.id,
            meetingGroup.creatorId,
            meetingGroup.name,
            meetingGroup.description,
            meetingGroup.location,
            meetingGroup.creationDate
        )
        meetingGroupWithNewMember.membersGroup() shouldContainExactly
                mutableListOf(
                    MemberMeetingGroup(meetingGroup.id, meetingGroup.creatorId),
                    MemberMeetingGroup(meetingGroup.id, MemberId(NEW_MEMBER_ID))
                )
    }

    @Test
    fun `fail when join a member has already joined`() {
        val meetingGroup = MeetingGroupMother.withMember(MEMBER_ID)

        val exception = shouldThrow<MemberHasAlreadyJoinedException> {
            meetingGroup.join(MemberId(MEMBER_ID))
        }

        exception.message shouldBe "Member '$MEMBER_ID' has already joined to group '${meetingGroup.id.value}'"
    }

    private companion object {
        private const val NEW_NAME = "new name"
        private const val NEW_DESCRIPTION = "new description"
        private const val NEW_COUNTRY = "new country"
        private const val NEW_CITY = "new city"
        private val NEW_LOCATION = MeetingGroupLocation(NEW_COUNTRY, NEW_CITY)
        private const val NEW_MEMBER_ID = "new member id"
        private const val MEMBER_ID = "member id"
    }
}