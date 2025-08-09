package dev.rennen.springdatamongo.controller;

import dev.rennen.springdatamongo.entity.People;
import dev.rennen.springdatamongo.repository.PeopleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <br/>
 * 2025/8/9
 *
 * @author rennen.dev
 */
@RestController
@RequiredArgsConstructor
public class HelloController {

    private final PeopleRepository peopleRepository;

    @GetMapping("/hello")
    public People hello() {
        peopleRepository.insert(new People("Walter", "White"));
        return peopleRepository.findByFirstName("Walter");
    }
}
