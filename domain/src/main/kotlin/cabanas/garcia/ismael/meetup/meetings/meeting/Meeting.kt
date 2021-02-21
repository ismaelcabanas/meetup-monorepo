package cabanas.garcia.ismael.meetup.meetings.meeting

import cabanas.garcia.ismael.meetup.meetings.configuration.MeetingGroupConfiguration
import cabanas.garcia.ismael.meetup.meetings.meetingcomment.MeetingComment
import cabanas.garcia.ismael.meetup.meetings.meetingcomment.MeetingCommentFactory
import cabanas.garcia.ismael.meetup.meetings.meetinggroup.MeetingGroup
import cabanas.garcia.ismael.meetup.meetings.member.MemberId
import cabanas.garcia.ismael.meetup.useraccess.userregistration.DomainEvent
import java.time.Instant

data class Meeting(
    val id: MeetingId,
    val startDate: Instant,
    val endDate: Instant,
    val cancelMemberId: MemberId? = null,
    val cancelDate: Instant? = null
) {
    private var events = mutableListOf<DomainEvent>()

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
        if (cancelDate.isBefore(startDate)) {
            throw MeetingCannotCanceledAfterStartException(id)
        }

        return Meeting(
            this.id,
            this.startDate,
            this.endDate,
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
