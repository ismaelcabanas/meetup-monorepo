package cabanas.garcia.ismael.meetup.meetings.domain.meeting

import cabanas.garcia.ismael.meetup.shared.domain.DomainException

class MeetingAttendeeMustBeAddedInEnrolmentTermException
    : DomainException("Attendee can be added only in enrolment term.")
