package com.demo.spring_boot_testing_demo.controllers;

import com.demo.spring_boot_testing_demo.domain.Asset;
import com.demo.spring_boot_testing_demo.services.AssetService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
class AssetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AssetService assetService;

    @Test
    void GetAssetById_happyPath() throws Exception {

        when(assetService.getAssetById(any())).thenReturn(Optional.of(new Asset(UUID.randomUUID(), "sensor")));

        var id = UUID.randomUUID();
        mockMvc.perform(get("/asset/" + id.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("sensor"));
    }

    @Test
    void GetAssetById_assetNotFound_shouldReturn404() throws Exception {
        when(assetService.getAssetById(any())).thenReturn(Optional.empty());

        var id = UUID.randomUUID();
        mockMvc.perform(get("/asset/" + id.toString()))
                .andExpect(status().isNotFound());
    }
}
