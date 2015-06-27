package tsuyoyo.fikaflag.domain

import com.fasterxml.jackson.annotation.JsonIgnore

import javax.validation.constraints.NotNull
import javax.validation.constraints.Null

class FikaFlag {
	
	Date date;

    @Null
    UUID uuid;

	@NotNull	
	String location;
	
	@NotNull
	String message;

	@NotNull
	@JsonIgnore
	final List<String> attendees = new ArrayList<String>();

	@NotNull
    String host;

}
