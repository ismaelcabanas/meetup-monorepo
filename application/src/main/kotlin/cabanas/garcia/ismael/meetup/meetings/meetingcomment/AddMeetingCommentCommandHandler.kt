package cabanas.garcia.ismael.meetup.meetings.meetingcomment

import cabanas.garcia.ismael.meetup.meetings.meeting.MeetingId
import cabanas.garcia.ismael.meetup.meetings.meeting.MeetingRepository
import cabanas.garcia.ismael.meetup.meetings.member.MemberId
import cabanas.garcia.ismael.meetup.shared.service.EventBus

class AddMeetingCommentCommandHandler(
    private val meetingRepository: MeetingRepository,
    private val meetingCommentRepository: MeetingCommentRepository,
    private val eventBus: EventBus) {

    fun handle(command: AddMeetingCommentCommand) {
        val meeting = meetingRepository.findById(MeetingId(command.meetingId))

        val meetingComment =
            meeting.addComment(MeetingCommentId(command.meetingCommentId), MemberId(command.userId), command.comment)

        meetingCommentRepository.save(meetingComment)

        eventBus.publish(meetingComment.events())
    }

}
