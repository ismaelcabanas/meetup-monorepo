package cabanas.garcia.ismael.meetup.administration.api.v1

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PatchMeetingGroupProposalController {
    @RequestMapping("/v1/administration/meeting-group-proposals/{meetingGroupProposalId}/accept")
    @PatchMapping
    fun execute(@PathVariable("meetingGroupProposalId") meetingGroupProposalId: String): ResponseEntity<Void> {
        return ResponseEntity(HttpStatus.OK)
    }
}