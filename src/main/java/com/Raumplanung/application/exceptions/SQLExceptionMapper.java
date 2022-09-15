package com.Raumplanung.application.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.sql.SQLException;

@Provider
public class SQLExceptionMapper implements ExceptionMapper<SQLException> {
	@Override
	public Response toResponse(SQLException exception) {
		int code = 500;
		if (exception.getErrorCode() == 19) code = 400;
		return Response.status(code).entity(new APIError(exception)).build();
	}
}
