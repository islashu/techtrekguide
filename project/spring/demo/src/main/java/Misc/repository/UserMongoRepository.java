package Misc.repository;

import Misc.model.UserMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserMongoRepository extends MongoRepository<UserMongo, Long> {

    // Return the following document with the email field only
    @Query("{'email': ?0}")
    UserMongo findByEmail(String email);
}
