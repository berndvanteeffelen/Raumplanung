package com.Raumplanung;

import com.Raumplanung.application.configurations.AbstractBinder;
import com.sun.net.httpserver.HttpServer;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;

import jakarta.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
public class RaumplanungApplication {
	private final static int PORT = 8080;

	public static void main(String[] args) {
		System.out.print("Starting server on port " + PORT + "...");
		URI uri = UriBuilder.fromUri("//localhost/").scheme("http").port(PORT).build();
		org.glassfish.jersey.server.ResourceConfig resourceConfig = new org.glassfish.jersey.server.ResourceConfig();//new ResourceConfig();
		resourceConfig.packages("com.Raumplanung.application;org.glassfish.jersey.examples.multipart");
		resourceConfig.register(new AbstractBinder());
		resourceConfig.register(MultiPartFeature.class);
		resourceConfig.register(new LoggingFeature(Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME), Level.INFO, LoggingFeature.Verbosity.PAYLOAD_TEXT, LoggingFeature.DEFAULT_MAX_ENTITY_SIZE));
		final HttpServer httpServer = JdkHttpServerFactory.createHttpServer(uri, resourceConfig, false);
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			System.out.print("Stopping server on port " + PORT + "...");
			httpServer.stop(0);
			System.out.println(" done.");
		}));
		httpServer.start();
		System.out.println(" done.");
		System.out.println("Waiting for requests...\n");
	}

}
