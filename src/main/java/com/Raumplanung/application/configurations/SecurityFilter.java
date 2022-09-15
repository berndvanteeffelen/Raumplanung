package com.Raumplanung.application.configurations;

import com.Raumplanung.application.services.BasicHTTPAuthenticationService;
import com.Raumplanung.application.services.CustomAuthorizationService;
import com.Raumplanung.model.NutzerRepository;
import com.Raumplanung.model.Rolle;
import jakarta.ws.rs.ForbiddenException;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.ext.Provider;
import org.glassfish.jersey.server.model.AnnotatedMethod;

import javax.annotation.Priority;
import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class SecurityFilter implements ContainerRequestFilter {
	@Context
	private ResourceInfo resourceInfo;
	@Inject
	private NutzerRepository nutzerRepository;
	@Inject
	private BasicHTTPAuthenticationService authenticationService;
	@Inject
	private CustomAuthorizationService authorizationService;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		SecurityContext securityContext = new SecurityContext();
		requestContext.setSecurityContext(securityContext);

		final AnnotatedMethod annotatedMethod = new AnnotatedMethod(resourceInfo.getResourceMethod());
		if (annotatedMethod.isAnnotationPresent(DenyAll.class)) throw new ForbiddenException("Forbidden");
		if (!annotatedMethod.isAnnotationPresent(RolesAllowed.class)) return;

		RolesAllowed annotation = annotatedMethod.getAnnotation(RolesAllowed.class);
		Set<Rolle> rolesAllowed = getRolesAllowedFromAnnotation(annotation);

		final String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		if (!authenticationService.validateHeader(authorizationHeader))
			throw new NotAuthorizedException(authenticationService.getWWWAuthenticateHeader());
		String base64EncodedCredentials = authorizationHeader.substring("Basic ".length());

		final int personalnummer = Integer.parseInt(authenticationService.getNameFromEncodedCredentials(base64EncodedCredentials));
		final String password = authenticationService.getPasswordFromEncodedCredentials(base64EncodedCredentials);
		if (!authenticationService.authenticate(personalnummer, password))
			throw new NotAuthorizedException(authenticationService.getWWWAuthenticateHeader());

		securityContext.setUser(nutzerRepository.findByPersonalnummer(personalnummer).orElse(null));

		if (!authorizationService.authorise(personalnummer, rolesAllowed)) throw new ForbiddenException("Forbidden");
	}

	private Set<Rolle> getRolesAllowedFromAnnotation(RolesAllowed annotation) {
		Set<Rolle> rolesAllowed = new HashSet<>();
		for (String value : annotation.value()) {
			rolesAllowed.add(Rolle.valueOf(value));
		}
		return rolesAllowed;
	}
}
