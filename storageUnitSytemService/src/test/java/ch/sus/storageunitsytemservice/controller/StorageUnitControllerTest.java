package ch.sus.storageunitsytemservice.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ch.sus.storageunitsytemservice.service.StorageUnitService;
import ch.sus.storageunitsytemservice.model.StorageUnit;
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

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = StorageUnitController.class)
@AutoConfigureMockMvc
public class StorageUnitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StorageUnitService storageUnitService;

    @Test
    public void whenStorageUnitControllerInjected_thenNotNull() {
        assert(storageUnitService != null);
    }

    @Test
    public void whenGetStorageUnitById_thenReturnStorageUnit() throws Exception {
        StorageUnit su = new StorageUnit();
        su.setId(1);
        su.setPricePerMonth(100.0);
        su.setSizeInM2(20.0);
        su.setAvailable(true);

        Mockito.when(storageUnitService.getStorageUnitById(1)).thenReturn(su);

        mockMvc.perform(get("/api/storageunits/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.pricePerMonth").value(100.0))
                .andExpect(jsonPath("$.available").value(true));
    }

    @Test
    public void whenGetStorageUnitByInvalidId_thenReturn404() throws Exception {
        Mockito.when(storageUnitService.getStorageUnitById(999)).thenThrow(new ResourceNotFoundException("Not found"));

        mockMvc.perform(get("/api/storageunits/999"))
                .andExpect(status().isNotFound());
    }
}
