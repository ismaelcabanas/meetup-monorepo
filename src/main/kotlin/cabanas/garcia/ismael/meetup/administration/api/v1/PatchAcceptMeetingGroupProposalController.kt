package cabanas.garcia.ismael.meetup.administration.api.v1

import cabanas.garcia.ismael.meetup.administration.application.acceptmeetupgroupproposal.AcceptMeetupGroupProposalCommand
import cabanas.garcia.ismael.meetup.meetings.domain.meetinggroupproposal.MeetingGroupProposalAlreadyAcceptedException
import cabanas.garcia.ismael.meetup.meetings.domain.meetinggroupproposal.MeetingGroupProposalNotProposedException
import cabanas.garcia.ismael.meetup.shared.api.errorhandling.ApiError
import cabanas.garcia.ismael.meetup.shared.application.CommandBus
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class PatchAcceptMeetingGroupProposalController(
    private val commandBus: CommandBus
) {
    @RequestMapping("/v1/administration/meeting-group-proposals/{meetingGroupProposalId}/accept")
    @PatchMapping
    fun execute(
        @RequestHeader("X-Meeting-User-Info") adminMemberId: String,
        @PathVariable("meetingGroupProposalId") meetingGroupProposalId: String
    ): ResponseEntity<Void> =
        commandBus
            .dispatch(AcceptMeetupGroupProposalCommand(meetingGroupProposalId, adminMemberId))
            .let {
                ResponseEntity(HttpStatus.OK)
            }

    @ExceptionHandler(value = [MeetingGroupProposalAlreadyAcceptedException::class])
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    fun handleMeetingGroupProposalAlreadyAcceptedException(): ApiError {
        return ApiError("Meeting group proposal already accepted.")
    }

    @ExceptionHandler(value = [MeetingGroupProposalNotProposedException::class])
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    fun handleMeetingGroupProposalNotProposedException(): ApiError {
        return ApiError("Meeting group proposal not proposed.")
    }
}