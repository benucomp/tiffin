package com.benu.code.controller;

import java.io.IOException;
import java.util.Arrays;
import java.lang.reflect.Method;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;

import org.glassfish.jersey.internal.util.Base64;

@Provider
public class PoweredByFilter implements ContainerRequestFilter{

	@Context
	private ResourceInfo resourceInfo;

	public static final String AUTH_HEADER_KEY = "Authorization";
	public static final String AUTH_HEADER_PREFIX = "Basic";
	public static final String SECURED_URL_PREFIX = "add";

	@Override
	public void filter(ContainerRequestContext req) throws IOException {

		Method method = resourceInfo.getResourceMethod();
		
		System.out.println("Method is " + method.getName());
		
		
		//Access allowed for all
		if(! method.isAnnotationPresent(PermitAll.class)) {

			//Access denied for all
			if(method.isAnnotationPresent(DenyAll.class))
			{                
				Response unauthorizedStatus = Response
						.status(Response.Status.FORBIDDEN)
						.entity("Access blocked for all users.")
						.build();
				req.abortWith(unauthorizedStatus);                
			}

			if (req.getUriInfo().getPath().contains(SECURED_URL_PREFIX)) {

				//Get request headers
				//final MultivaluedMap<String, String> headers = requestContext.getHeaders();

				List<String> authHeader = req.getHeaders().get(AUTH_HEADER_KEY);
				System.out.println("Authorization String is " + authHeader);

				if(authHeader == null || authHeader.isEmpty())
				{					
					Response unauthorizedStatus = Response
							.status(Response.Status.UNAUTHORIZED)
							.entity("You cannot access this resource")
							.build();
					req.abortWith(unauthorizedStatus);
				}

				if (authHeader != null && authHeader.size() > 0) {

					String authToken = authHeader.get(0);
					System.out.println("authToken is " + authToken);

					String base64Credentials = authToken.substring(AUTH_HEADER_PREFIX.length()).trim();
					System.out.println("base64Credentials : " + base64Credentials);

					String decodedString = Base64.decodeAsString(base64Credentials);
					System.out.println(decodedString);

					StringTokenizer tokenizer = new StringTokenizer(decodedString,":");
					while (tokenizer.hasMoreTokens()){
						System.out.println("-- String Sent from Client new--" + tokenizer.countTokens());

						System.out.println("-- req info --");
						//res.getHeaders().add("X-Powered-By", "Sachin");
						//System.out.println(res.getHeaders());

						String username = tokenizer.nextToken();
						System.out.println("User name is " + username);

						String password = tokenizer.nextToken();
						System.out.println("Password is " + password);
						System.out.println(method.isAnnotationPresent(RolesAllowed.class));
						
						//verify user access
						if(method.isAnnotationPresent(RolesAllowed.class)) {

							RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
							System.out.println("Roles Allowed are " + rolesAnnotation);
							
							Set<String> rolesSet = new HashSet<String> (Arrays.asList(rolesAnnotation.value())); 
							System.out.println("Roles Set is " + rolesSet);
							
							//Is user valid?
							if( ! isUserAllowed(username, password, rolesSet))
							{		
								
								Response unauthorizedStatus = Response
										.status(Response.Status.UNAUTHORIZED)
										.entity("User can not access the resource")
										.build();
								req.abortWith(unauthorizedStatus);
							}	
							System.out.println("Response is sent as the user is allowed");
						}
					}
				}
			}
		}
	}

	private boolean isUserAllowed(final String username, final String password, final Set<String> rolesSet)
	{
		boolean isAllowed = false;
		System.out.println("In isUserAllowed: User name is " + username);
		System.out.println("In isUserAllowed: password is " + password);
		System.out.println("In isUserAllowed: rolesSet is " + rolesSet);
		//Step 1. Fetch password from database and match with password in argument
		//If both match then get the defined role for user from database and continue; else return isAllowed [false]
		//Access the database and do this part yourself
		//String userRole = userMgr.getUserRole(username);

		if(username.equals("sachin") && password.equals("abc123"))
		{
			String userRole = "ADMIN";

			//Step 2. Verify user role
			if(rolesSet.contains(userRole))
			{
				isAllowed = true;
			}
		}
		System.out.println("Return is " + isAllowed);
		return isAllowed;
	}
}

