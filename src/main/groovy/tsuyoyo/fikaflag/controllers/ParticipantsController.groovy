package tsuyoyo.fikaflag.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import tsuyoyo.fikaflag.domain.JoinPost
import tsuyoyo.fikaflag.services.IFlagsService

import java.util.concurrent.Future

@RestController
class ParticipantsController {

    @Autowired
    IFlagsService flagsService;

    @RequestMapping(method = RequestMethod.POST, value="/join")
    @ResponseStatus(HttpStatus.CREATED)
    public Future<Integer> join(@RequestBody @Validated final JoinPost post,
                                @RequestParam(required = false) boolean now) {
        return flagsService.join(post, now);
    }

}
