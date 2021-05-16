package com.demo.spring_boot_testing_demo.domain.events;

import org.springframework.context.ApplicationEvent;

public class AssetCreatedEvent extends ApplicationEvent {
    private String name;

    public AssetCreatedEvent(Object source, String name) {
        super(source);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
