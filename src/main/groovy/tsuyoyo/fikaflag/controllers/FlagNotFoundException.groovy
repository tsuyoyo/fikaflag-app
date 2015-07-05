package tsuyoyo.fikaflag.controllers

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Flag was not found")
class FlagNotFoundException  extends RuntimeException {

}