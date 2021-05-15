package com.demo.spring_boot_testing_demo.repositories;

import com.demo.spring_boot_testing_demo.domain.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AssetRepository extends CrudRepository<Asset, UUID> {
}
