package daw.m3.uf6.objects.http;

import java.time.LocalDateTime;
import java.util.HashMap;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AppErrorResponse {
	
	@JsonIgnore
	private HttpStatus httpCode;
	
	private String code;
	private String message;
	private HashMap<String,String> details;
	private LocalDateTime timestamp;

	public AppErrorResponse(String code) {
		this.code = code;
		this.httpCode = ErrorMessage.valueOf(code).getHttpStatus();
		this.message = ErrorMessage.valueOf(code).getMessage();
		this.timestamp = LocalDateTime.now();
	}

	public HashMap<String,String> getDetails() {
		return details;
	}
	
	public void setDetails(HashMap<String,String> details) {
		this.details = details;
	}

	public HttpStatus getHttpCode() {
		return httpCode;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	
	
}
