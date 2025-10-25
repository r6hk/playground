package dev.rennen.springdatamongo.service;

import dev.rennen.springdatamongo.entity.People;
import dev.rennen.springdatamongo.repository.PeopleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.OffsetScrollPosition;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.domain.Window;
import org.springframework.stereotype.Service;

/**
 * <br/>
 * 2025/8/9
 *
 * @author rennen.dev
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class FooService implements CommandLineRunner {

    private final PeopleRepository peopleRepository;


    @Override
    public void run(String... args) {
        Window<People> peoples = peopleRepository.findFirst10ByName("@", ScrollPosition.offset());
        if (!peoples.isEmpty()) do {
            peoples = peopleRepository.findFirst10ByName(null, null);
            if (peoples.isEmpty()) break;
            for (var people : peoples) {
                System.out.println("people = " + people);
            }
            peoples = peopleRepository.findFirst10ByName("@")
        } while (!peoples.isEmpty() || peoples.hasNext());
        log.info("end!");
    }

}
