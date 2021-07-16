package cabanas.garcia.ismael.meetup.meetings.application.proposemeetinggroup

import cabanas.garcia.ismael.meetup.shared.MotherCreator
import java.util.UUID
import java.util.concurrent.TimeUnit

object ProposeMeetingGroupProposalCommandMother {
    fun random() =
        ProposeMeetingGroupProposalCommand.Builder(
            UUID.randomUUID().toString(),
            UUID.randomUUID().toString(),
            MotherCreator.faker().gameOfThrones().house(),
            MotherCreator.faker().lorem().sentence(),
            MotherCreator.faker().country().name(),
            MotherCreator.faker().country().capital(),
            MotherCreator.faker().date().future(30, TimeUnit.DAYS).toInstant()
        ).build()

    fun withoutMeetingGroupProposalId() =
        ProposeMeetingGroupProposalCommand.Builder()
            .withProposalMemberId(UUID.randomUUID().toString())
            .withMeetingGroupProposalName(MotherCreator.faker().gameOfThrones().house())
            .withMeetingGroupProposalDescription(MotherCreator.faker().lorem().sentence())
            .withMeetingGroupProposalCountry(MotherCreator.faker().country().countryCode2())
            .withMeetingGroupProposalCity(MotherCreator.faker().country().capital())
            .withMeetingGroupProposalDate(MotherCreator.faker().date().future(30, TimeUnit.DAYS).toInstant())
            .build()

    fun withoutMeetingGroupProposalMember() =
        ProposeMeetingGroupProposalCommand.Builder()
            .withMeetingGroupProposalId(UUID.randomUUID().toString())
            .withMeetingGroupProposalName(MotherCreator.faker().gameOfThrones().house())
            .withMeetingGroupProposalDescription(MotherCreator.faker().lorem().sentence())
            .withMeetingGroupProposalCountry(MotherCreator.faker().country().countryCode2())
            .withMeetingGroupProposalCity(MotherCreator.faker().country().capital())
            .withMeetingGroupProposalDate(MotherCreator.faker().date().future(30, TimeUnit.DAYS).toInstant())
            .build()

    fun withoutMeetingGroupProposalName() =
        ProposeMeetingGroupProposalCommand.Builder()
            .withMeetingGroupProposalId(UUID.randomUUID().toString())
            .withProposalMemberId(UUID.randomUUID().toString())
            .withMeetingGroupProposalDescription(MotherCreator.faker().lorem().sentence())
            .withMeetingGroupProposalCountry(MotherCreator.faker().country().countryCode2())
            .withMeetingGroupProposalCity(MotherCreator.faker().country().capital())
            .withMeetingGroupProposalDate(MotherCreator.faker().date().future(30, TimeUnit.DAYS).toInstant())
            .build()

    fun withoutMeetingGroupProposalDescription() =
        ProposeMeetingGroupProposalCommand.Builder()
            .withMeetingGroupProposalId(UUID.randomUUID().toString())
            .withProposalMemberId(UUID.randomUUID().toString())
            .withMeetingGroupProposalName(MotherCreator.faker().gameOfThrones().house())
            .withMeetingGroupProposalCountry(MotherCreator.faker().country().countryCode2())
            .withMeetingGroupProposalCity(MotherCreator.faker().country().capital())
            .withMeetingGroupProposalDate(MotherCreator.faker().date().future(30, TimeUnit.DAYS).toInstant())
            .build()

    fun withoutMeetingGroupProposalCountry() =
        ProposeMeetingGroupProposalCommand.Builder()
            .withMeetingGroupProposalId(UUID.randomUUID().toString())
            .withProposalMemberId(UUID.randomUUID().toString())
            .withMeetingGroupProposalName(MotherCreator.faker().gameOfThrones().house())
            .withMeetingGroupProposalDescription(MotherCreator.faker().lorem().sentence())
            .withMeetingGroupProposalCity(MotherCreator.faker().country().capital())
            .withMeetingGroupProposalDate(MotherCreator.faker().date().future(30, TimeUnit.DAYS).toInstant())
            .build()

    fun withoutMeetingGroupProposalCity() =
        ProposeMeetingGroupProposalCommand.Builder()
            .withMeetingGroupProposalId(UUID.randomUUID().toString())
            .withProposalMemberId(UUID.randomUUID().toString())
            .withMeetingGroupProposalName(MotherCreator.faker().gameOfThrones().house())
            .withMeetingGroupProposalDescription(MotherCreator.faker().lorem().sentence())
            .withMeetingGroupProposalCountry(MotherCreator.faker().country().countryCode2())
            .withMeetingGroupProposalDate(MotherCreator.faker().date().future(30, TimeUnit.DAYS).toInstant())
            .build()

    fun withoutMeetingGroupProposalDate() =
        ProposeMeetingGroupProposalCommand.Builder()
            .withMeetingGroupProposalId(UUID.randomUUID().toString())
            .withProposalMemberId(UUID.randomUUID().toString())
            .withMeetingGroupProposalName(MotherCreator.faker().gameOfThrones().house())
            .withMeetingGroupProposalDescription(MotherCreator.faker().lorem().sentence())
            .withMeetingGroupProposalCountry(MotherCreator.faker().country().countryCode2())
            .withMeetingGroupProposalCity(MotherCreator.faker().country().capital())
            .build()
}
