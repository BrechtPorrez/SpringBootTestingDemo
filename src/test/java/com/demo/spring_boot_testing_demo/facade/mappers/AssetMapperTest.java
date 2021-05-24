package com.demo.spring_boot_testing_demo.facade.mappers;

import com.demo.spring_boot_testing_demo.domain.Asset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class AssetMapperTest {

    private AssetMapper mapper;

    @BeforeEach
    void init() {
        mapper = new AssetMapperImpl();
    }

    @Test
    void assetMapper(){
        Asset asset = new Asset(UUID.randomUUID(), "my asset");

        var assetDto = mapper.map(asset);

        assertThat(asset.getName()).isEqualTo(assetDto.getName());
        assertThat(asset.getId()).isEqualTo(assetDto.getId());
    }
}
