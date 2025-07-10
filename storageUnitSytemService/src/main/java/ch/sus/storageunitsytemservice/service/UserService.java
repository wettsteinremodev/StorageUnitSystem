package ch.sus.storageunitsytemservice.service;

import ch.sus.storageunitsytemservice.model.User;
import ch.sus.storageunitsytemservice.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepo userRepo;

    @Autowired
    private UserService(UserRepo userRepo){
    this.userRepo= userRepo;
    }



    // Todo: Exception Handling
    // Todo: Some Sorting
    // Get all users
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        userRepo.findAll().forEach(list::add);
        return list;
    }

    // Todo: Exception Handling
    // Add
    public void save(User user) {
        userRepo.save(user);

    }

    // Get by ID
    public User getById(Integer id) {
        Optional <User> optionalUser = userRepo.findById(id);
        if (optionalUser.isPresent()){
            return optionalUser.get();
        } else {
            throw new RuntimeException("User with ID: " + id +" not Found");
        }
    }

    // Delete
    public void deleteById(Integer id) {
        if (!userRepo.existsById(id)) {
            throw new RuntimeException("User with ID: " + id + " not found");
        }

        try {
            userRepo.deleteById(id);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to delete user record with ID: " + id, ex);
        }
    }



    // Update
    public void updateById(User entity) {
        if(!userRepo.existsById(entity.getId()) || entity.getId() == null) {
            throw new RuntimeException("User with ID " + entity.getId() + " not found");
        }
        userRepo.save(entity);

    }
}
