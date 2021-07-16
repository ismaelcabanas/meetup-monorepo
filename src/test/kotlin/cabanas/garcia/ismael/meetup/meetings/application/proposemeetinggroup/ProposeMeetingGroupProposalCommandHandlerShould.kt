package cabanas.garcia.ismael.meetup.meetings.application.proposemeetinggroup

import cabanas.garcia.ismael.meetup.meetings.domain.meeting.MeetingGroupLocation
import cabanas.garcia.ismael.meetup.meetings.domain.meetinggroupproposal.*
import cabanas.garcia.ismael.meetup.meetings.domain.member.MemberId
import cabanas.garcia.ismael.meetup.shared.application.InvalidCommandException
import cabanas.garcia.ismael.meetup.shared.domain.service.EventBus
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class ProposeMeetingGroupProposalCommandHandlerShould {
    var meetingGroupProposalRepository = mockk<MeetingGroupProposalRepository>(relaxed = true)
    var eventBus = mockk<EventBus>(relaxed = true)

    @Test
    fun `save a meeting group proposal`() {
        val handler = ProposeMeetingGroupProposalCommandHandler(meetingGroupProposalRepository, eventBus)
        val command = ProposeMeetingGroupProposalCommandMother.random()

        handler.handle(command)

        val expectedMeetingGroupProposal =
            MeetingGroupProposal(
                MeetingGroupProposalId(command.meetingGroupProposalId!!),
                MemberId(command.proposalMemberId!!),
                command.meetingGroupProposalName!!,
                command.meetingGroupProposalDescription!!,
                MeetingGroupLocation(command.meetingGroupProposalCountry!!, command.meetingGroupProposalCity!!),
                command.meetingGroupProposalDate!!
            )
        shouldHaveSaved(expectedMeetingGroupProposal, MeetingGroupProposalState.PROPOSED)
    }

    @Test
    fun `publish meeting group proposal proposed event`() {
        val handler = ProposeMeetingGroupProposalCommandHandler(meetingGroupProposalRepository, eventBus)
        val command = ProposeMeetingGroupProposalCommandMother.random()

        handler.handle(command)

        val expectedMeetingGroupProposalProposed =
            MeetingGroupProposalProposed(
                command.meetingGroupProposalId!!,
                command.proposalMemberId!!,
                command.meetingGroupProposalName!!,
                command.meetingGroupProposalDescription!!,
                command.meetingGroupProposalCountry!!,
                command.meetingGroupProposalCity!!,
                command.meetingGroupProposalDate!!
            )
        shouldHavePublished(expectedMeetingGroupProposalProposed)
    }

    @Test
    fun `fail when propose a meeting group without identifier`() {
        val handler = ProposeMeetingGroupProposalCommandHandler(meetingGroupProposalRepository, eventBus)
        val command = ProposeMeetingGroupProposalCommandMother.withoutMeetingGroupProposalId()

        val exception = shouldThrowExactly<InvalidCommandException> {
            handler.handle(command)
        }

        exception shouldHaveMessage "Meeting group proposal identifier should be set."
    }

    private fun shouldHavePublished(expectedDomainEvent: MeetingGroupProposalProposed) {
        verify {
            eventBus.publish(
                withArg {
                    it shouldBe listOf(expectedDomainEvent)
                }
            )
        }
    }

    private fun shouldHaveSaved(expectedMeetingGroupProposal: MeetingGroupProposal, expectedState: MeetingGroupProposalState) {
        verify {
            meetingGroupProposalRepository.save(
                withArg {
                    it.id shouldBe expectedMeetingGroupProposal.id
                    it.proposalMemberId shouldBe expectedMeetingGroupProposal.proposalMemberId
                    it.name shouldBe expectedMeetingGroupProposal.name
                    it.meetingGroupLocation shouldBe expectedMeetingGroupProposal.meetingGroupLocation
                    it.proposalDate shouldBe expectedMeetingGroupProposal.proposalDate
                    it.state shouldBe expectedState
                }
            )
        }
    }
}