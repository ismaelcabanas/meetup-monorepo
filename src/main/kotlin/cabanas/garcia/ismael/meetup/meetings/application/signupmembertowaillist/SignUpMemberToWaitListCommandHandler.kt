package cabanas.garcia.ismael.meetup.meetings.application.signupmembertowaillist

import cabanas.garcia.ismael.meetup.meetings.domain.meeting.MeetingId
import cabanas.garcia.ismael.meetup.meetings.domain.meeting.MeetingRepository
import cabanas.garcia.ismael.meetup.meetings.domain.meetinggroup.MeetingGroupRepository
import cabanas.garcia.ismael.meetup.meetings.domain.member.MemberId
import cabanas.garcia.ismael.meetup.shared.domain.service.EventBus

class SignUpMemberToWaitListCommandHandler(
    private val meetingGroupRepository: MeetingGroupRepository,
    private val meetingRepository: MeetingRepository,
    private val eventBus: EventBus) {
    fun handle(command: SignUpMemberToWaitListCommand) {
        val meeting = meetingRepository.findById(MeetingId(command.meetingId))
        val meetingGroup = meetingGroupRepository.findBy(meeting.meetingGroup.id)

        meeting.signUpMemberToWaitList(meetingGroup, MemberId(command.memberId))

        meetingRepository.save(meeting)
        eventBus.publish(meeting.events())
    }

}
