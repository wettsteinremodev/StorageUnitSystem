package ch.sus.storageunitsytemservice.repository;

import ch.sus.storageunitsytemservice.model.Rented;
import ch.sus.storageunitsytemservice.model.StorageUnit;
import ch.sus.storageunitsytemservice.model.User;
import ch.sus.storageunitsytemservice.repo.RentedRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class RentedRepositoryTest {

    @Autowired
    private RentedRepo rentedRepo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void whenFindById_thenReturnRented() {
        // Create and persist User first
        User user = new User();
        user.setUserName("John Doe"); // set required fields as needed
        user.setEmail("john@example.com");
        user.setPassword("securePassword123");  // <-- set a valid password
        user = entityManager.persistAndFlush(user); // now user has an ID

        // Create and persist StorageUnit
        StorageUnit storageUnit = new StorageUnit();
        storageUnit.setSizeInM2(20.0);
        storageUnit.setAvailable(true);
        storageUnit.setName("Popo");
        storageUnit = entityManager.persistAndFlush(storageUnit);

        // Now create Rented with user and storageUnit set
        Rented rented = new Rented();
        rented.setUser(user);
        rented.setStorageUnit(storageUnit);
        rented.setStartDate(LocalDate.now());
        rented.setEndDate(LocalDate.now().plusDays(30));

        rented = entityManager.persistAndFlush(rented);

        Optional<Rented> found = rentedRepo.findById(rented.getId());
        assertTrue(found.isPresent());
        assertEquals(rented.getId(), found.get().getId());
        assertEquals(user.getId(), found.get().getUser().getId());
        assertEquals(storageUnit.getId(), found.get().getStorageUnit().getId());
    }

}

