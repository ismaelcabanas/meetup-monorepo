package cabanas.garcia.ismael.meetup.meetings.application.proposemeetinggroup

import cabanas.garcia.ismael.meetup.shared.MotherCreator
import java.util.UUID
import java.util.concurrent.TimeUnit

object ProposeMeetingGroupProposalCommandMother {
    fun random(): ProposeMeetingGroupProposalCommand {
        return ProposeMeetingGroupProposalCommand(
            UUID.randomUUID().toString(),
            UUID.randomUUID().toString(),
            MotherCreator.faker().gameOfThrones().house(),
            MotherCreator.faker().lorem().sentence(),
            MotherCreator.faker().country().name(),
            MotherCreator.faker().country().capital(),
            MotherCreator.faker().date().future(30, TimeUnit.DAYS).toInstant()
        )
    }

    fun withoutMeetingGroupProposalId(): ProposeMeetingGroupProposalCommand {
        return ProposeMeetingGroupProposalCommand(
            proposalMemberId = UUID.randomUUID().toString(),
            meetingGroupProposalName = MotherCreator.faker().gameOfThrones().house(),
            meetingGroupProposalDescription = MotherCreator.faker().lorem().sentence(),
            meetingGroupProposalCountry = MotherCreator.faker().country().name(),
            meetingGroupProposalCity = MotherCreator.faker().country().capital(),
            meetingGroupProposalDate = MotherCreator.faker().date().future(30, TimeUnit.DAYS).toInstant()
        )
    }

    fun withoutMeetingGroupProposalMember(): ProposeMeetingGroupProposalCommand {
        return ProposeMeetingGroupProposalCommand(
            meetingGroupProposalId = UUID.randomUUID().toString(),
            meetingGroupProposalName = MotherCreator.faker().gameOfThrones().house(),
            meetingGroupProposalDescription = MotherCreator.faker().lorem().sentence(),
            meetingGroupProposalCountry = MotherCreator.faker().country().name(),
            meetingGroupProposalCity = MotherCreator.faker().country().capital(),
            meetingGroupProposalDate = MotherCreator.faker().date().future(30, TimeUnit.DAYS).toInstant()
        )
    }

    fun withoutMeetingGroupProposalName(): ProposeMeetingGroupProposalCommand {
        return ProposeMeetingGroupProposalCommand(
            meetingGroupProposalId = UUID.randomUUID().toString(),
            proposalMemberId = UUID.randomUUID().toString(),
            meetingGroupProposalDescription = MotherCreator.faker().lorem().sentence(),
            meetingGroupProposalCountry = MotherCreator.faker().country().name(),
            meetingGroupProposalCity = MotherCreator.faker().country().capital(),
            meetingGroupProposalDate = MotherCreator.faker().date().future(30, TimeUnit.DAYS).toInstant()
        )
    }

    fun withoutMeetingGroupProposalDescription(): ProposeMeetingGroupProposalCommand {
        return ProposeMeetingGroupProposalCommand(
            meetingGroupProposalId = UUID.randomUUID().toString(),
            proposalMemberId = UUID.randomUUID().toString(),
            meetingGroupProposalName = MotherCreator.faker().gameOfThrones().house(),
            meetingGroupProposalCountry = MotherCreator.faker().country().name(),
            meetingGroupProposalCity = MotherCreator.faker().country().capital(),
            meetingGroupProposalDate = MotherCreator.faker().date().future(30, TimeUnit.DAYS).toInstant()
        )
    }

    fun withoutMeetingGroupProposalCountry(): ProposeMeetingGroupProposalCommand {
        return ProposeMeetingGroupProposalCommand(
            meetingGroupProposalId = UUID.randomUUID().toString(),
            proposalMemberId = UUID.randomUUID().toString(),
            meetingGroupProposalName = MotherCreator.faker().gameOfThrones().house(),
            meetingGroupProposalDescription = MotherCreator.faker().lorem().sentence(),
            meetingGroupProposalCity = MotherCreator.faker().country().capital(),
            meetingGroupProposalDate = MotherCreator.faker().date().future(30, TimeUnit.DAYS).toInstant()
        )
    }

    fun withoutMeetingGroupProposalCity(): ProposeMeetingGroupProposalCommand {
        return ProposeMeetingGroupProposalCommand(
            meetingGroupProposalId = UUID.randomUUID().toString(),
            proposalMemberId = UUID.randomUUID().toString(),
            meetingGroupProposalName = MotherCreator.faker().gameOfThrones().house(),
            meetingGroupProposalDescription = MotherCreator.faker().lorem().sentence(),
            meetingGroupProposalCountry = MotherCreator.faker().country().name(),
            meetingGroupProposalDate = MotherCreator.faker().date().future(30, TimeUnit.DAYS).toInstant()
        )
    }

    fun withoutMeetingGroupProposalDate(): ProposeMeetingGroupProposalCommand {
        return ProposeMeetingGroupProposalCommand(
            meetingGroupProposalId = UUID.randomUUID().toString(),
            proposalMemberId = UUID.randomUUID().toString(),
            meetingGroupProposalName = MotherCreator.faker().gameOfThrones().house(),
            meetingGroupProposalDescription = MotherCreator.faker().lorem().sentence(),
            meetingGroupProposalCountry = MotherCreator.faker().country().name(),
            meetingGroupProposalCity = MotherCreator.faker().country().capital()
        )
    }
}