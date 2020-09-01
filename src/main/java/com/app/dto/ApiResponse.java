package com.app.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

//@JsonInclude(Include.NON_NULL)
@Data
@NoArgsConstructor
public class ApiResponse {
	public Boolean isSuccess = Boolean.TRUE;
	public String message;
	public Object payLoad;
	public HttpStatus statusCode;
	public Long maxResult;

	public ApiResponse(Boolean isSuccess, String message) {
		this.isSuccess = isSuccess;
		this.message = message;
	}

	public ApiResponse(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public ApiResponse(Boolean isSuccess, String message, HttpStatus statusCode) {
		this.isSuccess = isSuccess;
		this.message = message;
		this.statusCode = statusCode;
	}

	public ApiResponse(String message, Object payLoad,HttpStatus statusCode) {
		this.message = message;
		this.payLoad = payLoad;
		this.statusCode = statusCode;
	}

	public ApiResponse(String message, Object payLoad,Long maxResult,HttpStatus statusCode) {
		this.message = message;
		this.payLoad = payLoad;
		this.statusCode = statusCode;
		this.maxResult = maxResult;
	}

}
