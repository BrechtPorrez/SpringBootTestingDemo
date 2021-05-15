package com.demo.spring_boot_testing_demo.domain;

import java.util.UUID;

public class Asset {

    private UUID id;
    private String name;

    public Asset(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
