package cabanas.garcia.ismael.meetup.meetings.application.signupmembertowaillist

import cabanas.garcia.ismael.meetup.meetings.domain.meeting.MeetingId
import cabanas.garcia.ismael.meetup.meetings.domain.meeting.MeetingRepository
import cabanas.garcia.ismael.meetup.meetings.domain.member.MemberId
import cabanas.garcia.ismael.meetup.shared.service.EventBus

class SignUpMemberToWaitListCommandHandler(
    private val meetingRepository: MeetingRepository,
    private val eventBus: EventBus) {
    fun handle(command: SignUpMemberToWaitListCommand) {
        val meeting = meetingRepository.findById(MeetingId(command.meetingId))

        meeting.signUpMemberToWaitList(MemberId(command.memberId))

        meetingRepository.save(meeting)
        eventBus.publish(meeting.events())
    }

}
