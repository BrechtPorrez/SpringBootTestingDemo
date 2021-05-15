package com.demo.spring_boot_testing_demo;

import com.demo.spring_boot_testing_demo.domain.Asset;
import com.demo.spring_boot_testing_demo.repositories.AssetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Transactional
class IntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private AssetRepository assetRepository;

    private Asset storedAsset;

    @BeforeEach
    void init(){
        storedAsset = assetRepository.save(new Asset("sensor"));
    }

    @Test
    void GetAssetById_ReturnsAsset(){

        var id = storedAsset.getId();

        var response = testRestTemplate.getForEntity("/asset/{id}", Asset.class, id);
        var asset = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(asset).isNotNull();
        assertThat(asset.getName()).isEqualTo("sensor");
        assertThat(asset.getId()).isEqualTo(id);
    }
}
