package com.demo.spring_boot_testing_demo.services;

import com.demo.spring_boot_testing_demo.domain.events.AssetCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EmailNotificationService {

    @EventListener
    public void handleAssetCreatedEvent(AssetCreatedEvent event){
        var temp = event.getSource();
        System.out.println(event.getName());
        System.out.println(event.getSource());
    }
}
