package ch.sus.storageunitsytemservice.repo;

import ch.sus.storageunitsytemservice.model.Rented;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentedRepo extends CrudRepository<Rented, Integer> {
}
