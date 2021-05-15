package com.demo.spring_boot_testing_demo.services;

import com.demo.spring_boot_testing_demo.domain.Asset;
import com.demo.spring_boot_testing_demo.repositories.AssetRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AssetService {

    private final AssetRepository assetRepository;

    public AssetService(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    public Asset getAssetById(UUID id) {
        return assetRepository.findById(id);
    }
}
