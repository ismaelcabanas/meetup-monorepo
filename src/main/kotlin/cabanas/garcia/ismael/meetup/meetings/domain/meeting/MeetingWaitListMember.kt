package cabanas.garcia.ismael.meetup.meetings.domain.meeting

import cabanas.garcia.ismael.meetup.meetings.domain.member.MemberId

data class MeetingWaitListMember(val meetingId: MeetingId, val memberId: MemberId) {
    companion object {
        fun create(meetingId: MeetingId, memberId: MemberId): MeetingWaitListMember =
            MeetingWaitListMember(meetingId, memberId)
    }
}