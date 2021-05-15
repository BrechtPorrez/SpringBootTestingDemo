package com.demo.spring_boot_testing_demo;

import com.demo.spring_boot_testing_demo.domain.Asset;
import com.demo.spring_boot_testing_demo.repositories.AssetRepository;
import com.demo.spring_boot_testing_demo.services.AssetService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
class CachingTest
{
    @Autowired
    private AssetService assetService;

    @MockBean
    private AssetRepository assetRepository;

    @Test
    void getAssetById_ReturnsCachedAsset(){
        given(assetRepository.findById(any())).willReturn(Optional.of(new Asset("sensor")));

        var id1 = UUID.randomUUID();
        assetService.getAssetById(id1);
        assetService.getAssetById(id1);

        var id2 = UUID.randomUUID();
        assetService.getAssetById(id2);

        assetService.getAssetById(id1);

        verify(assetRepository, times(1)).findById(id1);
        verify(assetRepository, times(1)).findById(id2);
    }
}
