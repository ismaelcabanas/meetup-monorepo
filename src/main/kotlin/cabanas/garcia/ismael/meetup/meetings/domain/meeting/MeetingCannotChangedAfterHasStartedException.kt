package cabanas.garcia.ismael.meetup.meetings.domain.meeting

import cabanas.garcia.ismael.meetup.shared.domain.DomainException

class MeetingCannotChangedAfterHasStartedException()
    : DomainException("Meeting cannot be changed after start.") {

}
