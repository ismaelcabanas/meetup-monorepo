package cabanas.garcia.ismael.meetup.meetings.api.v1

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Instant

@RestController
class PostMeetingGroupProposalController {
    @RequestMapping("/v1/meeting/meeting-group-proposals")
    @PostMapping
    fun execute(@RequestBody requestBody: PostMeetingGroupProposalRequest): ResponseEntity<Void> {
        return ResponseEntity(HttpStatus.CREATED)
    }
}

data class PostMeetingGroupProposalRequest(
    val meetingGroupProposalId: String?,
    val meetingGroupProposalName: String?,
    val meetingGroupProposalDescription: String?,
    val meetingGroupProposalCountry: String?,
    val meetingGroupProposalCity: String?,
    val meetingGroupProposalDate: Instant?
)