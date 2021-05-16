package com.demo.spring_boot_testing_demo.controllers;

import com.demo.spring_boot_testing_demo.domain.Asset;
import com.demo.spring_boot_testing_demo.services.AssetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Api(value = "DemoRestController")
@RestController
public class AssetController {

    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping("/asset/{id}")
    @ApiOperation(value = "Get an asset by assetId", response = Iterable.class, tags = "getAssetsById")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "Not authorized!"),
            @ApiResponse(code = 403, message = "Forbidden!"),
            @ApiResponse(code = 404, message = "Not found!") })
    public Asset getAssetById(@PathVariable UUID id){
        var asset = assetService.getAssetById(id);
        if(asset.isEmpty()){
            throw new AssetNotFoundException();
        }
        return asset.get();
    }
}
