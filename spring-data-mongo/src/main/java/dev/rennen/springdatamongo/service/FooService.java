package dev.rennen.springdatamongo.service;

import dev.rennen.springdatamongo.entity.People;
import dev.rennen.springdatamongo.repository.PeopleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * <br/>
 * 2025/8/9
 *
 * @author rennen.dev
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class FooService {

    private final PeopleRepository peopleRepository;

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.SECONDS)
    public void test() {
        log.info("insert");
        peopleRepository.insert(new People("1", "@"));
    }

}
