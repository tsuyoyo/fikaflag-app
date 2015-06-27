package tsuyoyo.fikaflag.domain

import javax.validation.constraints.NotNull;

class JoinPost {

	@NotNull
	UUID flagId;

	String name;

}
