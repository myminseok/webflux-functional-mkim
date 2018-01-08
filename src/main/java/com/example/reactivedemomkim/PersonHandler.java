/*
 * Copyright 2002-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.reactivedemomkim;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Component
public class PersonHandler {
	Logger logger = LoggerFactory.getLogger(PersonHandler.class);
	public Mono<ServerResponse> getPerson(ServerRequest request) {
		int personId = Integer.valueOf(request.pathVariable("id"));
		Mono<Person> personMono = Mono.just(new Person("James", 20));
		logger.info("request "+personId);
		return personMono
				.flatMap(person -> ServerResponse.ok().contentType(APPLICATION_JSON).body(fromObject(person)))
				.log()
				.switchIfEmpty(ServerResponse.notFound().build());
	}

	public Mono<ServerResponse> allPeople(ServerRequest request) {
		//Flux<Person> people = this.repository.allPeople();
		Flux<Person> people =
				Flux.just(new Person("James", 20), new Person("Oliver", 30));
		people.toIterable().forEach(System.out::println);
		return ServerResponse.ok().contentType(APPLICATION_JSON).body(people, Person.class);
	}

	public Mono<ServerResponse> savePerson(ServerRequest request) {
		//Mono<Person> person = request.bodyToMono(Person.class);
		//return ServerResponse.ok().build(this.repository.savePerson(person));
		return ServerResponse.ok().build();
	}



}
