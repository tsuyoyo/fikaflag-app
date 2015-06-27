package tsuyoyo.fikaflag.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus

import tsuyoyo.fikaflag.domain.FikaFlag
import tsuyoyo.fikaflag.domain.JoinPost
import tsuyoyo.fikaflag.services.IFlagsService
import tsuyoyo.fikaflag.websocket.ClientSocketHandler

import java.util.concurrent.Future

@Controller
public class FlagsController {

	@Autowired
	private ClientSocketHandler messageHandler;
	
	@Autowired
	IFlagsService flagsService;
	
	@RequestMapping(value="/flag", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public UUID submitFlag(@RequestBody @Validated FikaFlag flag) {
		UUID id = flagsService.post(flag);
		messageHandler.broadcastFlag(flag);
		return id;
	}



}
