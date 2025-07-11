package ch.sus.storageunitsytemservice.service;

import ch.sus.storageunitsytemservice.model.StorageUnit;
import ch.sus.storageunitsytemservice.repo.StorageUnitRepo;
import ch.sus.storageunitsytemservice.exception.ResourceNotFoundException;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StorageUnitService {

    private StorageUnitRepo storageRepo;


    @Autowired
    public StorageUnitService(StorageUnitRepo storageRepo, ListableBeanFactory listableBeanFactory){
        this.storageRepo = storageRepo;
    }

    // Get all StorageUnits from the repo
    public List<StorageUnit> getAllStorageUnits() {
        List<StorageUnit> list = new ArrayList<>();
        try{
            storageRepo.findAll().forEach(list::add);
        } catch (Exception ex){
            throw new RuntimeException("Could not Found the Storageunits");
        }
        return list;
    }

    // Get Sorted List
    public List<StorageUnit> getAllSorted(String sortedBy) {
        List<StorageUnit> sortedStorageunit = getAllStorageUnits(); // IMPORTANT: fetch data before sorting

        try{

            if (sortedBy == null)
                return sortedStorageunit;

            switch (sortedBy.toLowerCase()) {

                // Sort by Name
                case "name":
                    sortedStorageunit.sort((u1, u2) -> u1.getName().compareTo(u2.getName()));                     break;

                // Sort by Price
                case "price":
                    sortedStorageunit.sort((u1, u2) -> Double.compare(u1.getPricePerMonth(), u2.getPricePerMonth()));
                    break;

                // Sort by Size
                case "size":
                    sortedStorageunit.sort((u1, u2) -> Double.compare(u1.getSizeInM2(), u2.getSizeInM2()));
                    break;

                // Sort by Availability
                case "availability":
                    // Handle possible nulls safely, assuming getAvailable() returns Boolean
                    sortedStorageunit.sort((u1, u2) -> Boolean.compare(
                            Boolean.TRUE.equals(u1.getAvailable()),
                            Boolean.TRUE.equals(u2.getAvailable())
                    ));
                    break;

                default:
                    sortedStorageunit.sort((u1, u2) -> Boolean.compare(
                            Boolean.TRUE.equals(u1.getAvailable()),
                            Boolean.TRUE.equals(u2.getAvailable())
                    ));
                    break;
            }

        } catch (Exception ex){
            throw new RuntimeException("Could not Found the Storageunits");

        }

        return sortedStorageunit;
    }

    // Todo: Exception handling
    // Save or update a StorageUnit
    public void saveStorageUnit(StorageUnit entity) {
        storageRepo.save(entity);
    }


    // Get StorageUnit by ID
    public StorageUnit getStorageUnitById(Integer id) {
        Optional<StorageUnit> optionalStorageUnit = storageRepo.findById(id);
        if (optionalStorageUnit.isPresent()) {
            return optionalStorageUnit.get();
        } else {
            throw new ResourceNotFoundException("StorageUnit with ID: " + id + " not found");
        }
    }

    // Delete StorageUnit by ID
    public void deleteStorageUnitById(Integer id) {
        if (!storageRepo.existsById(id)) {
            throw new RuntimeException("StorageUnit with ID: " + id + " not found");
        }

        try {
            storageRepo.deleteById(id);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to delete StorageUnit with ID: " + id, ex);
        }
    }

    // Update StorageUnit (same as save)
    // Update StorageUnit
    public void updateStorageUnitById(StorageUnit entity) {
        Integer id = entity.getId();
        if (id == null || !storageRepo.existsById(id)) {
            throw new RuntimeException("StorageUnit with ID: " + id + " not found");
        }

        try {
            storageRepo.save(entity);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to update StorageUnit with ID: " + id, ex);
        }
    }
}
