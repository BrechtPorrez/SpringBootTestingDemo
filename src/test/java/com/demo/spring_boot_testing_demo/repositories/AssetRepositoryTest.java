package com.demo.spring_boot_testing_demo.repositories;

import com.demo.spring_boot_testing_demo.domain.Asset;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class AssetRepositoryTest {

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void findByName_happyPath(){
        var savedAsset = testEntityManager.persistFlushFind(new Asset("sensor"));
        var assetOptional = assetRepository.findById(savedAsset.getId());

        assertTrue(assetOptional.isPresent());

        var asset = assetOptional.get();
        assertThat(asset.getId().equals(savedAsset.getId()));
        assertThat(asset.getName().equals(savedAsset.getName()));
    }

    @Test
    void findByName_assetNotFound_returnsEmptyOptional(){
        var assetOptional = assetRepository.findById(UUID.randomUUID());

        assertTrue(assetOptional.isEmpty());
    }

    @Test
    void findAll_happyPath(){
        var savedAssets = List.of(
                new Asset("sensor1"),
                new Asset("sensor2")
        );
        savedAssets.forEach(asset -> testEntityManager.persistAndFlush(asset));
        var foundAssets = assetRepository.findAll();

        assertThat(foundAssets).hasSameElementsAs(savedAssets);
    }

    @Test
    void findAff_emptyList(){
        List<Asset> foundAssets = assetRepository.findAll();

        assertTrue(foundAssets.isEmpty());
    }
}
