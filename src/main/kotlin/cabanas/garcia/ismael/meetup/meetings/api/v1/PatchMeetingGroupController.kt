package cabanas.garcia.ismael.meetup.meetings.api.v1

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PatchMeetingGroupController {
    @RequestMapping("/v1/meeting/meeting-groups/{meetingGroupId}/join")
    @PatchMapping
    fun execute(@PathVariable("meetingGroupId") meetingGroupId: String): ResponseEntity<Void> {
        return ResponseEntity(HttpStatus.OK)
    }
}