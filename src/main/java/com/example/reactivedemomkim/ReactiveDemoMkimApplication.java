package com.example.reactivedemomkim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


@SpringBootApplication
public class ReactiveDemoMkimApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveDemoMkimApplication.class, args);
	}

	@Bean
	public RouterFunction<?> routes(PersonHandler handler) {
		return RouterFunctions.route(GET("/person/{id}"), handler::getPerson)
				.and(route(GET("/person"), handler::allPeople)
						.and(route(POST("/person"), handler::savePerson)));
	}

}
