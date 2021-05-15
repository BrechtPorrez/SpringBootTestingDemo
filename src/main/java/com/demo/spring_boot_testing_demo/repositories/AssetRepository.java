package com.demo.spring_boot_testing_demo.repositories;

import com.demo.spring_boot_testing_demo.domain.Asset;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AssetRepository {
    Asset findById(UUID id);
}
