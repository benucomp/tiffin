package com.benu.code.config;

import org.glassfish.jersey.server.ResourceConfig;
import com.benu.code.controller.AppController;

public class ApplicationConfig extends ResourceConfig {

	public ApplicationConfig() {
		register(AppController.class);
	}
}
