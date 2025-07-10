package ch.sus.storageunitsytemservice.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ch.sus.storageunitsytemservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import org.junit.jupiter.api.extension.ExtendWith;

import ch.sus.storageunitsytemservice.model.User;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    // Test 1: Check controller loads
    @Test
    public void whenUserControllerInjected_thenNotNull() {
        assert(userService != null);
    }

    // Test 2: GET user by valid ID returns user JSON
    @Test
    public void whenGetUserById_thenReturnUser() throws Exception {
        User mockUser = new User();
        mockUser.setId(1);
        mockUser.setUserName("testuser");
        mockUser.setEmail("test@example.com");
        mockUser.setPassword("pass");

        Mockito.when(userService.getById(1)).thenReturn(mockUser);

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userName").value("testuser"))
                .andExpect(jsonPath("$.email").value("test@example.com"));
    }

    // Test 3: GET user by invalid ID returns 404
    @Test
    public void whenGetUserByInvalidId_thenReturn404() throws Exception {
        Mockito.when(userService.getById(100)).thenThrow(new ResourceNotFoundException("User not found"));

        mockMvc.perform(get("/api/users/100"))
                .andExpect(status().isNotFound());
    }
}

