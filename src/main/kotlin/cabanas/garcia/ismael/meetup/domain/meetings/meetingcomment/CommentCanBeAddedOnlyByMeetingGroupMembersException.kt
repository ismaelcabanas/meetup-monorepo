package cabanas.garcia.ismael.meetup.domain.meetings.meetingcomment

import cabanas.garcia.ismael.meetup.domain.meetings.meetinggroup.MeetingGroupId
import cabanas.garcia.ismael.meetup.domain.meetings.member.MemberId

class CommentCanBeAddedOnlyByMeetingGroupMembersException(val userId: MemberId, val meetingGroupId: MeetingGroupId)
    : Exception ("User '${userId.value}' is not member of meeting group '${meetingGroupId.value}'."){
}