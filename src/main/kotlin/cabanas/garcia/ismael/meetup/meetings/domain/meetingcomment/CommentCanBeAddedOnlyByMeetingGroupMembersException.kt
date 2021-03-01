package cabanas.garcia.ismael.meetup.meetings.domain.meetingcomment

import cabanas.garcia.ismael.meetup.meetings.domain.meetinggroup.MeetingGroupId
import cabanas.garcia.ismael.meetup.meetings.domain.member.MemberId

class CommentCanBeAddedOnlyByMeetingGroupMembersException(val userId: MemberId, val meetingGroupId: MeetingGroupId)
    : Exception ("User '${userId.value}' is not member of meeting group '${meetingGroupId.value}'."){
}