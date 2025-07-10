package ch.sus.storageunitsytemservice.controller;

import ch.sus.storageunitsytemservice.model.StorageUnit;
import ch.sus.storageunitsytemservice.service.StorageUnitService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/storageunits")
@CrossOrigin
public class StorageUnitController {

    @Autowired
    private StorageUnitService storageUnitService;

    public StorageUnitController(StorageUnitService storageUnitService) {
        this.storageUnitService = storageUnitService;
    }

    // Get all storage units, optionally sorted
    @GetMapping("")
    public List<StorageUnit> getAllSorted(@RequestParam(required = false) String sortBy) {
        return storageUnitService.getAllSorted(sortBy);
    }

    // Get one by ID
    @GetMapping("/{id}")
    public StorageUnit getById(@PathVariable Integer id) {
        return storageUnitService.getStorageUnitById(id);
    }

    // Create
    @PostMapping("")
    public String save(@Valid @RequestBody StorageUnit storageUnit) {
        storageUnitService.saveStorageUnit(storageUnit);
        return "Storage unit created successfully";
    }

    // Update
    @PutMapping("/{id}")
    public String update(@PathVariable Integer id, @Valid @RequestBody StorageUnit storageUnit) {
        storageUnit.setId(id);
        storageUnitService.updateStorageUnitById(storageUnit);
        return "Storage unit updated successfully";
    }

    // Delete
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        storageUnitService.deleteStorageUnitById(id);
        return "Storage unit deleted successfully";
    }
}
