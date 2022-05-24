package com.hatthabank.adminbackend.message;

import com.hatthabank.sdk.web.exception.errorcode.ErrorMessageCode;

public enum MessageCode implements ErrorMessageCode {
	NOT_FOUND("EUR001", "Record is not found."),
	ALREADY_EXIST("EUR002", "Record is already exists."),
	BAD_REQUEST("EUR003", "Bad Request Body."),
	PARENT_ENTITY_NOT_FOUND("EUR004", "Parent entity is not found."),
	DUPLICATED("EUR004", "Record is duplicate."),
	NOT_ALLOWED("EUR005", "Record is not allowed to update.");

	String code;
	String message;

	@Override
	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	MessageCode(String code, String message) {
		this.code = code;
		this.message = message;
	}
}
