package ch.sus.storageunitsytemservice.service;

import ch.sus.storageunitsytemservice.model.Rented;
import ch.sus.storageunitsytemservice.repo.RentedRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RentedService {

    @Autowired
    private RentedRepo rentedRepo;

    public RentedService(RentedRepo rentedRepo) {
        this.rentedRepo = rentedRepo;
    }

    // Get all rented records
    public List<Rented> getAllRented() {
        try {
            List<Rented> list = new ArrayList<>();
            rentedRepo.findAll().forEach(list::add);
            return list;
        } catch (Exception ex) {
            throw new RuntimeException("Failed to fetch rented records", ex);
        }
    }

    // Create new rented record
    public void save(Rented entity) {
        if (entity.getUser() == null || entity.getStorageUnit() == null) {
            throw new RuntimeException("Rented must have a valid User and StorageUnit");
        }

        try {
            rentedRepo.save(entity);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to save rented record", ex);
        }
    }

    // Get by ID
    public Rented getById(Integer id) {
        return rentedRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Rented with ID: " + id + " not found"));
    }

    // Delete by ID
    public void deleteById(Integer id) {
        if (!rentedRepo.existsById(id)) {
            throw new RuntimeException("Rented with ID: " + id + " not found");
        }

        try {
            rentedRepo.deleteById(id);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to delete rented record with ID: " + id, ex);
        }
    }

    // Update rented record
    public void updateById(Rented entity) {
        Integer id = entity.getId();

        if (id == null || !rentedRepo.existsById(id)) {
            throw new RuntimeException("Rented with ID: " + id + " not found");
        }

        if (entity.getUser() == null || entity.getStorageUnit() == null) {
            throw new RuntimeException("Rented must have a valid User and StorageUnit");
        }

        try {
            rentedRepo.save(entity);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to update rented record with ID: " + id, ex);
        }
    }
}
