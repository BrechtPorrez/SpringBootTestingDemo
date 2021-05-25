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

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
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
        assetRepository.deleteAll();
        storedAsset = assetRepository.save(new Asset("sensor1"));
        assetRepository.save(new Asset("sensor2"));
    }

    @Test
    void getAssetById_ReturnsAsset(){

        var id = storedAsset.getId();

        var response = testRestTemplate.getForEntity("/assets/{id}", Asset.class, id);
        var asset = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(asset).isNotNull();
        assertThat(asset.getName()).isEqualTo("sensor1");
        assertThat(asset.getId()).isEqualTo(id);
    }

    @Test
    void getAllAssets_ReturnsList(){
        var response = testRestTemplate.getForEntity("/assets", Asset[].class);
        var assets = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo((HttpStatus.OK));
        assertThat(assets).isNotNull();
        assertThat(assets.length).isEqualTo(2);
        assertThat(assets[0].getName()).isEqualTo("sensor1");
        assertThat(assets[1].getName()).isEqualTo("sensor2");
    }
}
