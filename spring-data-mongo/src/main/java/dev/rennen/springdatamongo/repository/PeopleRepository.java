package dev.rennen.springdatamongo.repository;

import dev.rennen.springdatamongo.entity.People;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.domain.Window;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * <br/>
 * 2025/8/9
 *
 * @author rennen.dev
 */
public interface PeopleRepository extends MongoRepository<People, String>, CustomPeopleRepository {

    Window<People> findFirst10ByName(String name, ScrollPosition position);


}

interface CustomPeopleRepository {

    @Nullable
    People findOneByName(String name);

}

@RequiredArgsConstructor
class CustomPeopleRepositoryImpl implements CustomPeopleRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public People findOneByName(String name) {
        return null;
    }

}