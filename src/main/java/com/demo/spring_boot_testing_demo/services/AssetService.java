package com.demo.spring_boot_testing_demo.services;

import com.demo.spring_boot_testing_demo.domain.Asset;
import com.demo.spring_boot_testing_demo.domain.events.AssetCreatedEvent;
import com.demo.spring_boot_testing_demo.repositories.AssetRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AssetService {

    private final ApplicationEventPublisher publisher;
    private final AssetRepository assetRepository;

    public AssetService(AssetRepository assetRepository, ApplicationEventPublisher publisher) {
        this.assetRepository = assetRepository;
        this.publisher = publisher;
    }

    @Cacheable(cacheNames = "assets")
    public Optional<Asset> getAssetById(UUID id) {
        publisher.publishEvent(new AssetCreatedEvent(this, "created"));
        return assetRepository.findById(id);
    }

    public List<Asset> findAllAssets() {
        return assetRepository.findAll();
    }
}
