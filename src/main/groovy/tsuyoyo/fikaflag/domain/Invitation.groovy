package tsuyoyo.fikaflag.domain

import groovy.transform.Canonical

import com.fasterxml.jackson.annotation.JsonIgnore


/**
 * Front end側と共有する型
 * 
 */
@Canonical
class Invitation {

	String createdby;
	
	String message;
	
	Date startTime;
	
}
