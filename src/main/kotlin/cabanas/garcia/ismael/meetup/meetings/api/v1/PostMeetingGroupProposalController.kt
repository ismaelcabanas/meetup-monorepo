package cabanas.garcia.ismael.meetup.meetings.api.v1

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Instant
import javax.validation.Valid
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@RestController
class PostMeetingGroupProposalController {
    @RequestMapping("/v1/meeting/meeting-group-proposals")
    @PostMapping
    fun execute(@Valid @RequestBody requestBody: PostMeetingGroupProposalRequest): ResponseEntity<Void> {
        return ResponseEntity(HttpStatus.CREATED)
    }
}

data class PostMeetingGroupProposalRequest(
    @get:NotEmpty(message = "Meeting group proposal identifier cannot be null.")
    val meetingGroupProposalId: String? = null,
    @get:NotEmpty(message = "Meeting group proposal name cannot be null.")
    val meetingGroupProposalName: String? = null,
    @get:NotEmpty(message = "Meeting group proposal description cannot be null.")
    val meetingGroupProposalDescription: String? = null,
    @get:NotEmpty(message = "Meeting group proposal country cannot be null.")
    val meetingGroupProposalCountry: String? = null,
    @get:NotEmpty(message = "Meeting group proposal city cannot be null.")
    val meetingGroupProposalCity: String? = null,
    @get:NotNull(message = "Meeting group proposal date cannot be null.")
    val meetingGroupProposalDate: Instant? = null
)