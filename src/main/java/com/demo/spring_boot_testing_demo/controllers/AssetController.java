package com.demo.spring_boot_testing_demo.controllers;

import com.demo.spring_boot_testing_demo.domain.Asset;
import com.demo.spring_boot_testing_demo.services.AssetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class AssetController {

    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping("/asset/{id}")
    public Asset getAssetById(@PathVariable UUID id){
        var asset = assetService.getAssetById(id);
        if(asset.isEmpty()){
            throw new AssetNotFoundException();
        }
        return asset.get();
    }
}
