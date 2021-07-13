package cabanas.garcia.ismael.meetup.meetings.domain.meetinggroupproposal

import cabanas.garcia.ismael.meetup.meetings.domain.meeting.MeetingGroupLocation
import cabanas.garcia.ismael.meetup.meetings.domain.member.MemberId
import cabanas.garcia.ismael.meetup.shared.MotherCreator
import java.time.Instant
import java.util.*
import java.util.concurrent.TimeUnit

object MeetingGroupProposalMother {
    private const val SOME_NAME = "some name"
    private const val SOME_DESCRIPTION = "some description"
    private const val SOME_COUNTRY = "some country"
    private const val SOME_CITY = "some city"
    private const val SOME_MEMBER_ID = "some member id"
    private const val SOME_DATE = "2021-01-25T13:13:03Z"

    fun proposed(meetingGroupProposalId: String): MeetingGroupProposal =
        MeetingGroupProposal(
            MeetingGroupProposalId(meetingGroupProposalId),
            MemberId(SOME_MEMBER_ID),
            SOME_NAME,
            SOME_DESCRIPTION,
            MeetingGroupLocation(SOME_COUNTRY, SOME_CITY),
            Instant.parse(SOME_DATE)
        ).propose()

    fun accepted(meetingGroupProposalId: String): MeetingGroupProposal =
        MeetingGroupProposal(
            MeetingGroupProposalId(meetingGroupProposalId),
            MemberId(SOME_MEMBER_ID),
            SOME_NAME,
            SOME_DESCRIPTION,
            MeetingGroupLocation(SOME_COUNTRY, SOME_CITY),
            Instant.parse(SOME_DATE)
        ).propose().accept()

    fun random(): MeetingGroupProposal =
        MeetingGroupProposal(
            MeetingGroupProposalId(UUID.randomUUID().toString()),
            MemberId(UUID.randomUUID().toString()),
            MotherCreator.faker().name().name(),
            MotherCreator.faker().lorem().sentence(),
            MeetingGroupLocation(
                MotherCreator.faker().country().countryCode2(),
                MotherCreator.faker().country().capital()
            ),
            MotherCreator.faker().date().future(30, TimeUnit.DAYS).toInstant()
        )
}