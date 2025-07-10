package ch.sus.storageunitsytemservice.controller;


import ch.sus.storageunitsytemservice.model.Rented;
import ch.sus.storageunitsytemservice.service.RentedService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rented") // Overallmapping for rented
@CrossOrigin
public class RentedController {

    @Autowired
    private RentedService rentedService;

    //Konstruktor
    public RentedController(RentedService rentedService){
        this.rentedService= rentedService;
    }


    @GetMapping  // sort by still here for later
    public List<Rented> getAll() {
        return rentedService.getAllRented();
    }



    // Geting Rent by ID
    @GetMapping("/{id}")
    public Rented getRentedById(@PathVariable Integer id){
        return rentedService.getById(id);
    }
    // Adding a Rent
    @PostMapping
    public String createRented(@Valid @RequestBody Rented rented){
        rentedService.save(rented);
        return "Saved Successfully";
    }

    // Deleting Rent by ID
    @DeleteMapping("/{id}")
    public String deleteRented(@PathVariable Integer id){
        rentedService.deleteById(id);
        return "Deleted Succesfully";
    }
    // Updating Rent by ID
    @PutMapping("/{id}")    public String upDateRented (@ Valid @PathVariable Integer id, @RequestBody Rented rented){
        rented.setId(id); // id wird gesetzt
        rentedService.updateById(rented);
        return "Updated Succesfully";
    }


}
