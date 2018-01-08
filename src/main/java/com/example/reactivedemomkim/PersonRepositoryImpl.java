package com.example.reactivedemomkim;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class PersonRepositoryImpl implements PersonRepository{

    @Override
    public Mono<Person> getPerson(int id) {
        return Mono.just(new Person("James", 20));
    }

    @Override
    public Flux<Person> allPeople() {
        return  Flux.just(new Person("James", 20), new Person("Oliver", 30));
    }

    @Override
    public Mono<Void> savePerson(Mono<Person> person) {
        return Mono.empty();
    }
}
