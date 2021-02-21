package cabanas.garcia.ismael.meetup.meetings.meeting

import cabanas.garcia.ismael.meetup.meetings.configuration.MeetingGroupConfiguration
import cabanas.garcia.ismael.meetup.meetings.meetingcomment.MeetingComment
import cabanas.garcia.ismael.meetup.meetings.meetingcomment.MeetingCommentFactory
import cabanas.garcia.ismael.meetup.meetings.meetinggroup.MeetingGroup
import cabanas.garcia.ismael.meetup.meetings.member.MemberId
import cabanas.garcia.ismael.meetup.useraccess.userregistration.DomainEvent
import java.time.Instant

class Meeting private constructor(
    val id: MeetingId,
    val term: MeetingTerm,
    val cancelMemberId: MemberId? = null,
    val cancelDate: Instant? = null
) {
    private var events = mutableListOf<DomainEvent>()

    companion object {
        fun create(id: MeetingId, term: MeetingTerm) = Meeting(
            id,
            term
        )
    }

    fun addComment(
        meetingCommentId: String,
        authorId: String,
        comment: String,
        meetingGroup: MeetingGroup,
        meetingGroupConfiguration: MeetingGroupConfiguration
    ): MeetingComment =
        MeetingCommentFactory.create(
            meetingCommentId,
            id.value,
            authorId,
            comment,
            meetingGroup,
            meetingGroupConfiguration
        )

    fun cancel(cancelMemberId: MemberId, cancelDate: Instant): Meeting {
        if (!term.isAfterStart(cancelDate)) {
            throw MeetingCannotCanceledAfterStartException(id)
        }

        return Meeting(
            this.id,
            this.term,
            cancelMemberId,
            cancelDate
        ).also {
            it.events = this.events
            it.registerDomainEvent(
                MeetingCanceled(
                    this.id.value,
                    it.cancelMemberId!!.value,
                    it.cancelDate!!
                )
            )
        }
    }

    fun events() = events

    private fun registerDomainEvent(domainEvent: DomainEvent) {
        events.add(domainEvent)
    }
}
