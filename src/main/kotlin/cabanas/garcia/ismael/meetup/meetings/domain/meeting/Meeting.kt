package cabanas.garcia.ismael.meetup.meetings.domain.meeting

import cabanas.garcia.ismael.meetup.meetings.domain.configuration.MeetingGroupConfiguration
import cabanas.garcia.ismael.meetup.meetings.domain.meeting.events.MeetingWaitListMemberAdded
import cabanas.garcia.ismael.meetup.meetings.domain.meetingcomment.MeetingComment
import cabanas.garcia.ismael.meetup.meetings.domain.meetingcomment.MeetingCommentFactory
import cabanas.garcia.ismael.meetup.meetings.domain.meetingcomment.MeetingCommentId
import cabanas.garcia.ismael.meetup.meetings.domain.meetinggroup.MeetingGroup
import cabanas.garcia.ismael.meetup.meetings.domain.member.MemberId
import cabanas.garcia.ismael.meetup.shared.domain.DomainEvent
import java.time.Instant

class Meeting private constructor(
    val id: MeetingId,
    val meetingGroup: MeetingGroup,
    val meetingAttendeeLimits: MeetingAttendeeLimits,
    val enrolmentTerm: EnrolmentTerm,
    val meetingTerm: MeetingTerm,
    val cancelMemberId: MemberId? = null,
    val cancelDate: Instant? = null
) {
    private var attendees = mutableListOf<MeetingAttendee>()
    private var waitListMembers = mutableListOf<MeetingWaitListMember>()
    private var events = mutableListOf<DomainEvent>()

    companion object {
        fun create(
            id: MeetingId,
            meetingGroup: MeetingGroup,
            meetingAttendeeLimits: MeetingAttendeeLimits,
            enrolmentTerm: EnrolmentTerm,
            meetingTerm: MeetingTerm
        ) = Meeting(
            id,
            meetingGroup,
            meetingAttendeeLimits,
            enrolmentTerm,
            meetingTerm
        )
    }

    fun addComment(
        meetingCommentId: MeetingCommentId,
        authorId: MemberId,
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
        if (!meetingTerm.isAfterStart(cancelDate)) {
            throw MeetingCannotChangedAfterHasStartedException()
        }

        return Meeting(
            this.id,
            this.meetingGroup,
            this.meetingAttendeeLimits,
            this.enrolmentTerm,
            this.meetingTerm,
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

    fun removeAttendee(
        attendeeId: MemberId,
        removingPersonId: MemberId,
        removingDate: Instant,
        reason: String? = null
    ): Meeting {
        if (meetingTerm.isAfterStart(removingDate)) {
            throw MeetingCannotChangedAfterHasStartedException()
        }
        if (attendees.find { meetingAttendee -> meetingAttendee.id == attendeeId } == null) {
            throw OnlyMeetingAttendeesCanBeRemovedException(id, attendeeId)
        }
        if (reason == null) {
            throw ReasonOfRemovingAttendeeFromMeetingMustBeProvidedException(id, attendeeId)
        }

        val attendeeRemoved = findAttendee(attendeeId).remove(removingPersonId, removingDate, reason)

        replaceAttendee(attendeeId, attendeeRemoved)

        return Meeting(
            id,
            meetingGroup,
            meetingAttendeeLimits,
            enrolmentTerm,
            meetingTerm
        ).also {
            it.attendees = this.attendees
            it.events = this.events
            it.registerDomainEvent(
                MeetingAttendeeRemoved(
                    this.id.value,
                    attendeeId.value,
                    removingPersonId.value,
                    removingDate,
                    reason
                )
            )
        }
    }

    fun addAttendee(attendeeId: MemberId, enrolmentDate: Instant) =
        addAttendee(attendeeId, enrolmentDate, 0)

    fun addAttendee(attendeeId: MemberId, enrolmentDate: Instant, guestsNumber: Int): Meeting {
        checkMeetingCannotChangedAfterHasStarted(enrolmentDate)
        checkMeetingAttendeeMustBeAddedInEnrolmentTerm(enrolmentDate)
        if (!meetingGroup.isMemberMeetingGroup(attendeeId)) {
            throw MeetingAttendeeMustBeAMemberOfMeetingGroupException()
        }
        if (meetingAttendeeLimits.guestsAboveOf(guestsNumber)) {
            throw MeetingGuestsNumberIsAboveLimitException()
        }
        if (meetingAttendeeLimits.attendeesAboveOf(currentMeetingAttendeesNumber() + 1 + guestsNumber)) {
            throw MeetingAttendeesNumberIsAboveLimitException()
        }

        attendees.add(MeetingAttendee(attendeeId, id, enrolmentDate, guestsNumber))

        registerDomainEvent(
            MeetingAttendeeAdded(
                attendeeId.value,
                id.value,
                enrolmentDate
            )
        )

        return Meeting(
            id,
            meetingGroup,
            meetingAttendeeLimits,
            enrolmentTerm,
            meetingTerm
        ).also {
            it.events = this.events
            it.attendees = this.attendees
        }
    }

    fun signUpMemberToWaitList(meetingGroup: MeetingGroup, memberId: MemberId) {
        checkMeetingCannotChangedAfterHasStarted(Instant.now())
        checkMeetingAttendeeMustBeAddedInEnrolmentTerm(Instant.now())
        checkMemberOnWaitListMustBeMemberOfMeetingGroup(meetingGroup, memberId)
        checkCannotBeMoreThanOnceOnWaitList(memberId)

        waitListMembers.add(MeetingWaitListMember.create(this.id, memberId))

        registerDomainEvent(MeetingWaitListMemberAdded(id.value, memberId.value))
    }

    fun attendees() = attendees

    fun waitListMembers() = waitListMembers

    fun events() = events


    private fun checkCannotBeMoreThanOnceOnWaitList(memberId: MemberId) {
        if (waitListMembers.firstOrNull { it.memberId == memberId } != null) {
            throw MemberOnWaitListAlreadyExistException()
        }
    }

    private fun checkMemberOnWaitListMustBeMemberOfMeetingGroup(meetingGroup: MeetingGroup, memberId: MemberId) {
        if (!meetingGroup.isMemberMeetingGroup(memberId)) {
            throw MemberOnWaitListMustBeMemberOfMeetingGroupException()
        }
    }

    private fun checkMeetingCannotChangedAfterHasStarted(enrolmentDate: Instant) {
        if (meetingTerm.isAfterStart(enrolmentDate)) {
            throw MeetingCannotChangedAfterHasStartedException()
        }
    }

    private fun checkMeetingAttendeeMustBeAddedInEnrolmentTerm(enrolmentDate: Instant) {
        if (!enrolmentTerm.isInTerm(enrolmentDate)) {
            throw MeetingAttendeeMustBeAddedInEnrolmentTermException()
        }
    }

    private fun currentMeetingAttendeesNumber() =
        attendees.sumBy { meetingAttendee -> meetingAttendee.attendeeWithGuestsNumber()  }

    private fun findAttendee(attendeeId: MemberId): MeetingAttendee =
        attendees.find {
                attendee -> attendee.id == attendeeId
        }!!

    private fun replaceAttendee(attendeeIdToReplace: MemberId, newAttendee: MeetingAttendee) {
        attendees.remove(findAttendee(attendeeIdToReplace))
        attendees.add(newAttendee)
    }

    private fun registerDomainEvent(domainEvent: DomainEvent) {
        events.add(domainEvent)
    }
}
