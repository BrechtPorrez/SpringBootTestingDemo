package com.demo.spring_boot_testing_demo.facade.mappers;

import com.demo.spring_boot_testing_demo.domain.Asset;
import com.demo.spring_boot_testing_demo.facade.dto.AssetDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AssetMapper {
    AssetDto map(Asset asset);
}
