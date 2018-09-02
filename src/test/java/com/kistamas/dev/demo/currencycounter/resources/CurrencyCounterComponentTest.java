package com.kistamas.dev.demo.currencycounter.resources;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CurrencyCounterComponentTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetCUCcount() {

        ResponseEntity<String> responseEntity =
                restTemplate.getForEntity("/count/CUC/9", String.class);

        String result = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("{\"1\":1,\"3\":1,\"5\":1}", result);
    }

    @Test
    public void testGetHUFcount() {

        ResponseEntity<String> responseEntity =
                restTemplate.getForEntity("/count/HUF/11", String.class);

        String result = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("{\"1\":1,\"10\":1}", result);
    }

    @Test
    public void testGetHUF2count() {

        ResponseEntity<String> responseEntity =
                restTemplate.getForEntity("/count/HUF2/13", String.class);

        String result = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("{\"5\":1,\"10\":1}", result);
    }
}