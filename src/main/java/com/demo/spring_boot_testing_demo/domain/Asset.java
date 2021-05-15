package com.demo.spring_boot_testing_demo.domain;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "ASSET")
public class Asset {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;
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
