package rc;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServerRepository extends MongoRepository<Server,String> {
    Optional<Server> findById(String id);

    List<Server> findByName(String name);
}
