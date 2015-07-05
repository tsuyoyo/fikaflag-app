package tsuyoyo.fikaflag.controllers

import org.codehaus.groovy.runtime.StringGroovyMethods
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import tsuyoyo.fikaflag.domain.FikaFlag
import tsuyoyo.fikaflag.domain.JoinPost
import tsuyoyo.fikaflag.services.IFlagsService
import tsuyoyo.fikaflag.websocket.ClientSocketHandler

import javax.validation.constraints.NotNull
import java.util.concurrent.Future

@RestController
public class FlagsController {

	@Autowired
	private ClientSocketHandler messageHandler;
	
	@Autowired
	IFlagsService flagsService;

	@RequestMapping(value="/flag", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<FikaFlag> getFlag() {
		return flagsService.get();
	}

	@RequestMapping(value="/flag/{id}", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public FikaFlag getFlag(@PathVariable("id") String id) {
		FikaFlag flag = flagsService.get(UUID.fromString(id));
		if (!flag) {
			throw new FlagNotFoundException();
		}
		return flag;
	}

	@RequestMapping(value="/flag", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public String submitFlag(@RequestBody @Validated FikaFlag flag) {
		UUID id = flagsService.post(flag);
		messageHandler.broadcastFlag(flag);
		return id.toString();
	}

	@RequestMapping(value="/flag/{id}", method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void rejectFlag(@PathVariable("id") String id) {
		flagsService.delete(UUID.fromString(id));
        messageHandler.broadcastClose();
	}

}
