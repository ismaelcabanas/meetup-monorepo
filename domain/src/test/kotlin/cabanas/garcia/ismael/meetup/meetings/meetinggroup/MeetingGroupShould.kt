package cabanas.garcia.ismael.meetup.meetings.meetinggroup

import cabanas.garcia.ismael.meetup.meetings.meeting.MeetingGroupLocation
import io.kotest.matchers.collections.shouldContain
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
    }

    private companion object {
        private const val NEW_NAME = "new name"
        private const val NEW_DESCRIPTION = "new description"
        private const val NEW_COUNTRY = "new country"
        private const val NEW_CITY = "new city"
        private val NEW_LOCATION = MeetingGroupLocation(NEW_COUNTRY, NEW_CITY)
    }
}