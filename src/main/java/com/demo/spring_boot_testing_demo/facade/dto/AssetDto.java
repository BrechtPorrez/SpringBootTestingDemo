package com.demo.spring_boot_testing_demo.facade.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.UUID;

public class AssetDto {

    @ApiModelProperty(notes = "UUID of the asset",name="id",required=true,value="test id")
    private UUID id;

    @ApiModelProperty(notes = "Name of the asset",name="name",required=true,value="test name")
    private String name;

    public AssetDto() {
    }

    public AssetDto(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
