package com.l24o.serials.rest;


import com.l24o.serials.rest.response.ResponseCreator;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import static com.l24o.serials.rest.exceptions.Error.SERVER_ERROR;

public class CustomExceptionMapper implements ExceptionMapper<Exception> {
	@Context
	private HttpHeaders requestHeaders;
	
	private String getHeaderVersion() {		
		return "0.1";
	}
	
    public Response toResponse(Exception ex) {
    	System.out.println(ex.getMessage() + ex.getCause());
        return ResponseCreator.error(500, SERVER_ERROR.getCode(), getHeaderVersion());
    }
}