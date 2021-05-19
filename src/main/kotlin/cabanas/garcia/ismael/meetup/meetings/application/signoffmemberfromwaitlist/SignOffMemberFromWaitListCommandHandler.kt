package cabanas.garcia.ismael.meetup.meetings.application.signoffmemberfromwaitlist

import cabanas.garcia.ismael.meetup.meetings.domain.meeting.MeetingId
import cabanas.garcia.ismael.meetup.meetings.domain.meeting.MeetingRepository
import cabanas.garcia.ismael.meetup.meetings.domain.member.MemberId
import cabanas.garcia.ismael.meetup.shared.domain.service.EventBus

class SignOffMemberFromWaitListCommandHandler(
    private val meetingRepository: MeetingRepository,
    private val eventBus: EventBus
) {
    fun handle(command: SignOffMemberFromWaitListCommand) {
        val meeting = meetingRepository.findById(MeetingId(command.meetingId))

        meeting.signOffMemberFromWaitList(MemberId(command.memberId))

        meetingRepository.save(meeting)

        eventBus.publish(meeting.pullEvents())
    }
}