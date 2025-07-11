package ch.sus.storageunitsytemservice.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ch.sus.storageunitsytemservice.service.RentedService;
import ch.sus.storageunitsytemservice.model.Rented;
import ch.sus.storageunitsytemservice.model.User;
import ch.sus.storageunitsytemservice.model.StorageUnit;
import ch.sus.storageunitsytemservice.exception.ResourceNotFoundException;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = RentedController.class)
@AutoConfigureMockMvc
public class RentedControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RentedService rentedService;

    @Test
    public void whenRentedControllerInjected_thenNotNull() {
        assert(rentedService != null);
    }

    @Test
    public void whenGetRentedById_thenReturnRented() throws Exception {
        Rented rented = new Rented();
        rented.setId(1);
        rented.setStartDate(LocalDate.of(2025,7,5));
        rented.setEndDate(LocalDate.of(2025,8,5));

        User user = new User();
        user.setId(1);
        rented.setUser(user);

        StorageUnit su = new StorageUnit();
        su.setId(1);
        rented.setStorageUnit(su);

        Mockito.when(rentedService.getById(1)).thenReturn(rented);

        mockMvc.perform(get("/api/rented/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.user.id").value(1))
                .andExpect(jsonPath("$.storageUnit.id").value(1))
                .andExpect(jsonPath("$.startDate").value("2025-07-05"));
    }

    @Test
    public void whenGetRentedByInvalidId_thenReturn404() throws Exception {
        Mockito.when(rentedService.getById(999)).thenThrow(new ResourceNotFoundException("Not found"));

        mockMvc.perform(get("/api/rented/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Not found"));
    }
}