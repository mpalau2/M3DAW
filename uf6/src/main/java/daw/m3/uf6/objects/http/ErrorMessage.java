package daw.m3.uf6.objects.http;

import org.springframework.http.HttpStatus;

public enum ErrorMessage {
	E001("E001",HttpStatus.BAD_REQUEST,"Error en la petició rebuda d'alta usuari"),
	E002("E002",HttpStatus.BAD_REQUEST,"Error en la petició rebuda"),
	E300("E300",HttpStatus.INTERNAL_SERVER_ERROR,"Error intern executant la petició"),
	E404("E404",HttpStatus.NOT_FOUND,"No s'han trobat dades coincidents");

	private final String code;
	private final HttpStatus httpStatus;
	private final String message;
	
	private ErrorMessage(String code, HttpStatus httpStatus, String message) {
		this.code = code;
		this.httpStatus = httpStatus;
		this.message = message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public String getMessage() {
		return message;
	}
}
