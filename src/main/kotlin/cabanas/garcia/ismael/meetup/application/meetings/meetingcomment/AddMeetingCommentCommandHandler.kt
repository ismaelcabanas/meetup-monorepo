package cabanas.garcia.ismael.meetup.application.meetings.meetingcomment

import cabanas.garcia.ismael.meetup.domain.meetings.meeting.MeetingId
import cabanas.garcia.ismael.meetup.domain.meetings.meeting.MeetingRepository
import cabanas.garcia.ismael.meetup.domain.meetings.meetingcomment.MeetingCommentId
import cabanas.garcia.ismael.meetup.domain.meetings.meetingcomment.MeetingCommentRepository
import cabanas.garcia.ismael.meetup.domain.meetings.member.MemberId
import cabanas.garcia.ismael.meetup.domain.shared.service.EventBus

class AddMeetingCommentCommandHandler(
    private val meetingRepository: MeetingRepository,
    private val meetingCommentRepository: MeetingCommentRepository,
    private val eventBus: EventBus
) {

    fun handle(command: AddMeetingCommentCommand) {
        /*
        val meeting = meetingRepository.findById(MeetingId(command.meetingId))

        val meetingComment =
            meeting.addComment(MeetingCommentId(command.meetingCommentId), MemberId(command.userId), command.comment)

        meetingCommentRepository.save(meetingComment)

        eventBus.publish(meetingComment.events())

         */
    }

}
