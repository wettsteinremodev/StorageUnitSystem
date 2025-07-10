package ch.sus.storageunitsytemservice.repo;

import ch.sus.storageunitsytemservice.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Integer> {
    Integer id(Integer id);
}
