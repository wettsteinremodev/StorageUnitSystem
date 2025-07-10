package ch.sus.storageunitsytemservice.repo;

import ch.sus.storageunitsytemservice.model.StorageUnit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageUnitRepo extends CrudRepository<StorageUnit, Integer> {
}
