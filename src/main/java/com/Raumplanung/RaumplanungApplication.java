package com.Raumplanung;

import com.Raumplanung.application.configurations.ResourceConfig;
import com.sun.net.httpserver.HttpServer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

@SpringBootApplication
public class RaumplanungApplication {
	private final static int PORT = 8080;

	public static void main(String[] args) {
		System.out.print("Starting server on port " + PORT + "...");
		URI uri = UriBuilder.fromUri("//localhost/").scheme("http").port(PORT).build();
		org.glassfish.jersey.server.ResourceConfig resourceConfig = new ResourceConfig();
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
