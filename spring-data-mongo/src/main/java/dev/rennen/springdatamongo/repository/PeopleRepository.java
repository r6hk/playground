package dev.rennen.springdatamongo.repository;

import dev.rennen.springdatamongo.entity.People;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * <br/>
 * 2025/8/9
 *
 * @author rennen.dev
 */
public interface PeopleRepository extends MongoRepository<People, String>, CustomPeopleRepository {
}

interface CustomPeopleRepository {

    People findByFirstName(String firstName);
}

@RequiredArgsConstructor
class CustomPeopleRepositoryImpl implements CustomPeopleRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public People findByFirstName(String firstName) {
        return mongoTemplate.findOne(new Query(Criteria.where("firstName").is(firstName)), People.class);
    }

}