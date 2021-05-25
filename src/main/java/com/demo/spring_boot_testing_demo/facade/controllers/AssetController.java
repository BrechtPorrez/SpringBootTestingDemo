package com.demo.spring_boot_testing_demo.facade.controllers;

import com.demo.spring_boot_testing_demo.facade.dto.AssetDto;
import com.demo.spring_boot_testing_demo.facade.mappers.AssetMapper;
import com.demo.spring_boot_testing_demo.services.AssetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Api(value = "DemoRestController")
@RestController
public class AssetController {

    private final AssetService assetService;
    private final AssetMapper assetMapper;

    public AssetController(AssetService assetService, AssetMapper assetMapper) {
        this.assetService = assetService;
        this.assetMapper = assetMapper;
    }

    @GetMapping("/assets/{id}")
    @ApiOperation(value = "Get an asset by assetId", response = Iterable.class, tags = "getAssetsById")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "Not found!") })
    public AssetDto getAssetById(@PathVariable UUID id){
        var asset = assetService.getAssetById(id);
        if(asset.isEmpty()){
            throw new AssetNotFoundException();
        }
        return assetMapper.map(asset.get());
    }

    @GetMapping("/assets")
    public List<AssetDto> getAllAssets(){
        var assets = assetService.findAllAssets();
        return assets.stream()
                .map(assetMapper::map)
                .collect(toList());
    }
}
