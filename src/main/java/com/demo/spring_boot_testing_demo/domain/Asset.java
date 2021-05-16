package com.demo.spring_boot_testing_demo.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "ASSET")
public class Asset {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @ApiModelProperty(notes = "UUID of the asset",name="id",required=true,value="test id")
    private UUID id;

    @ApiModelProperty(notes = "Name of the asset",name="name",required=true,value="test name")
    private String name;

    public Asset() {
    }

    public Asset(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public Asset(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
