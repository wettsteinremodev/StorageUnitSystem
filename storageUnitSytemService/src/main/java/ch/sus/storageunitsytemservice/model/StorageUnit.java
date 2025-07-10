package ch.sus.storageunitsytemservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "storage_unit")
public class StorageUnit {

    // Params
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "The name should not be empty")
    @Size(min=2,max=20, message = "The name should not be shorter than 2 or longer than 20")
    @Column(name = "name")
    private String name;


    @DecimalMax(value = "1000.00", message = "The size should smaller than 1000.00.")
    @DecimalMin(value = "1.0", message = "The size should be bigger than 1.0.")
    @Column(name = "size_in_m2")
    private Double sizeInM2;

    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0.")
    @Column(name = "price_per_month")
    private Double pricePerMonth;

    @NotNull(message = "Availabitlity should not be null")
    @Column(name = "is_available")
    private Boolean isAvailable;

    // This is for the bidirektional connection between Rented and Storageunit
    @OneToMany(mappedBy = "storageUnit", cascade = CascadeType.ALL)
    @JsonBackReference // To prevent infinite loop
    private List<Rented> rentedList = new ArrayList<>();


    // Getter und Setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSizeInM2() {
        return sizeInM2;
    }

    public void setSizeInM2(Double sizeInM2) {
        this.sizeInM2 = sizeInM2;
    }

    public Double getPricePerMonth() {
        return pricePerMonth;
    }

    public void setPricePerMonth(Double pricePerMonth) {
        this.pricePerMonth = pricePerMonth;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public List<Rented> getRentedList() {
        return rentedList;
    }

    public void setRentedList(List<Rented> rentedList) {
        this.rentedList = rentedList;
    }
}
