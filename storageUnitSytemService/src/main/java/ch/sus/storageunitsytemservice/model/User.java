package ch.sus.storageunitsytemservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="app_user")
public class User {

    // Params
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "User cannot be null")
    @Size(min=1, max = 15, message = "Name should be between 1 and 15")
    @Column(name = "user_name")
    private String userName;

    @NotBlank(message = "Email is required")
    @Email (message = "the email should have a propper format")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min =8,max=20, message = "the password should be between 8 and 20 chars")
    @Column(name = "password")
    private String password;

    // This is for the bideirektional Talking between Rented and User
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference // To prevent infinite loop
    private List<Rented> rentedList = new ArrayList<>();

    // Getter und Setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Rented> getRentedList() {
        return rentedList;
    }

    public void setRentedList(List<Rented> rentedList) {
        this.rentedList = rentedList;
    }
}
