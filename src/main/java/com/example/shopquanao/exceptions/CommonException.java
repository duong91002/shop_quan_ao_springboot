/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.exceptions;

import org.springframework.http.HttpStatus;

/**
 *
 * @author haidu
 */
public class CommonException extends RuntimeException{
 
	private static final long serialVersionUID = 1L;
	private int code;
    private HttpStatus httpStatus; 
    private String message;

    public CommonException() {
        super();
    }

    public CommonException(HttpStatus httpStatus, String message) {
        super(message); 
        this.code = httpStatus.value();
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
