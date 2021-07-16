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
    private val handler = ProposeMeetingGroupProposalCommandHandler(meetingGroupProposalRepository, eventBus)

    @Test
    fun `save a meeting group proposal`() {
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
        val command = ProposeMeetingGroupProposalCommandMother.withoutMeetingGroupProposalId()

        val exception = shouldThrowExactly<InvalidCommandException> {
            handler.handle(command)
        }

        exception shouldHaveMessage "Meeting group proposal identifier is required."
    }

    @Test
    fun `fail when propose a meeting group without member`() {
        val command = ProposeMeetingGroupProposalCommandMother.withoutMeetingGroupProposalMember()

        val exception = shouldThrowExactly<InvalidCommandException> {
            handler.handle(command)
        }

        exception shouldHaveMessage "Meeting group proposal member is required."
    }

    @Test
    fun `fail when propose a meeting group without name`() {
        val command = ProposeMeetingGroupProposalCommandMother.withoutMeetingGroupProposalName()

        val exception = shouldThrowExactly<InvalidCommandException> {
            handler.handle(command)
        }

        exception shouldHaveMessage "Meeting group proposal name is required."
    }

    @Test
    fun `fail when propose a meeting group without description`() {
        val command = ProposeMeetingGroupProposalCommandMother.withoutMeetingGroupProposalDescription()

        val exception = shouldThrowExactly<InvalidCommandException> {
            handler.handle(command)
        }

        exception shouldHaveMessage "Meeting group proposal description is required."
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