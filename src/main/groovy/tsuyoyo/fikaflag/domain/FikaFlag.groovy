package tsuyoyo.fikaflag.domain

import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

class FikaFlag {
	
	Date date;

	@NotNull	
	String location;
	
	@NotNull
	String message;

	@JsonIgnore
	List<String> attendees;
}
