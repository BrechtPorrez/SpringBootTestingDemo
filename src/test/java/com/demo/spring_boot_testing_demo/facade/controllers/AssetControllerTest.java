package com.demo.spring_boot_testing_demo.facade.controllers;

import com.demo.spring_boot_testing_demo.domain.Asset;
import com.demo.spring_boot_testing_demo.facade.dto.AssetDto;
import com.demo.spring_boot_testing_demo.facade.mappers.AssetMapper;
import com.demo.spring_boot_testing_demo.services.AssetService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest
class AssetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AssetService assetService;
    @MockBean
    private AssetMapper assetMapper;

    @Test
    void GetAssetById_happyPath() throws Exception {
        var id = UUID.randomUUID();

        when(assetService.getAssetById(any())).thenReturn(Optional.of(new Asset(id, "sensor")));
        when(assetMapper.map(any())).thenReturn(new AssetDto(id, "sensor"));

        mockMvc.perform(get("/assets/" + id.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("sensor"));
    }

    @Test
    void GetAssetById_assetNotFound_shouldReturn404() throws Exception {
        when(assetService.getAssetById(any())).thenReturn(Optional.empty());

        var id = UUID.randomUUID();
        mockMvc.perform(get("/assets/" + id.toString()))
                .andExpect(status().isNotFound());
    }

    @Test
    void GetAllAssets_happyPath() throws Exception {
        var originalAssets = List.of(
                new Asset("sensor1"),
                new Asset("sensor2"));
        when(assetService.findAllAssets()).thenReturn(originalAssets);
        when(assetMapper.map(originalAssets.get(0))).thenReturn(new AssetDto(UUID.randomUUID() ,"sensor1"));
        when(assetMapper.map(originalAssets.get(1))).thenReturn(new AssetDto(UUID.randomUUID() ,"sensor2"));

        mockMvc.perform((get("/assets")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0]['name']").value("sensor1"))
                .andExpect(jsonPath("$.[1]['name']").value("sensor2"));
    }

    @Test
    void GetAllAssets_emptyList() throws Exception {
        when(assetService.findAllAssets()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/assets"))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }
}
