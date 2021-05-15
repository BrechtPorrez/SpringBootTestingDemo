package com.demo.spring_boot_testing_demo.services;

import com.demo.spring_boot_testing_demo.domain.Asset;
import com.demo.spring_boot_testing_demo.repositories.AssetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AssetServiceTest {

    @Mock
    private AssetRepository assetRepository;

    private AssetService assetService;

    @BeforeEach
    void SetUp(){
        assetService = new AssetService(assetRepository);
    }

    @Test
    void getAssetById_happyPath(){
        var id  = UUID.randomUUID();
        when(assetRepository.findById(any())).thenReturn(Optional.of(new Asset(id, "sensor")));

        var asset = assetService.getAssetById(UUID.randomUUID());

        assertTrue(asset.isPresent());
        assertThat(asset.get().getName()).isEqualTo("sensor");
        assertThat(asset.get().getId()).isEqualTo(id);
    }

    @Test
    void getAssetById_assetNotFound_shouldReturnNull(){
        when(assetRepository.findById(any())).thenReturn(null);

        var asset = assetService.getAssetById(UUID.randomUUID());

        assertThat(asset).isEqualTo(null);
    }

}
