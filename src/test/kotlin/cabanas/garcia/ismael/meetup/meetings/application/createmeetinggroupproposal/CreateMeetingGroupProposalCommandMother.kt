package cabanas.garcia.ismael.meetup.meetings.application.createmeetinggroupproposal

import cabanas.garcia.ismael.meetup.shared.MotherCreator
import java.util.UUID
import java.util.concurrent.TimeUnit

object CreateMeetingGroupProposalCommandMother {
    fun random(): CreateMeetingGroupProposalCommand {
        return CreateMeetingGroupProposalCommand(
            UUID.randomUUID().toString(),
            UUID.randomUUID().toString(),
            MotherCreator.faker().gameOfThrones().house(),
            MotherCreator.faker().lorem().sentence(),
            MotherCreator.faker().country().name(),
            MotherCreator.faker().country().capital(),
            MotherCreator.faker().date().future(30, TimeUnit.DAYS).toInstant()
        )
    }
}