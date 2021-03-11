package com.example.demo.controller;

import com.example.demo.DemoApplication;
import com.example.demo.exception.ErrorInfo;
import com.example.demo.model.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoControllerIntegrationTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpEntity<String> httpEntity;
    HttpHeaders httpHeaders = new HttpHeaders();
    ObjectMapper objectMapper = new ObjectMapper();


    @Test
    public void findPair_giftCardBalance_0() throws IOException {

        String uri = "demo/findPair/0";

        LinkedMultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
        parameters.add("file", new ClassPathResource("products.csv"));

        HttpEntity<LinkedMultiValueMap<String, Object>> entity = new HttpEntity<>(parameters, httpHeaders);

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort(uri), HttpMethod.POST, entity, String.class, "");

        ErrorInfo errorInfo = objectMapper.readValue(response.getBody(), ErrorInfo.class);

        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void findPair_giftCardBalance_50() throws IOException {

        String uri = "demo/findPair/50";

        LinkedMultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
        parameters.add("file", new ClassPathResource("products.csv"));

        HttpEntity<LinkedMultiValueMap<String, Object>> entity = new HttpEntity<>(parameters, httpHeaders);

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort(uri), HttpMethod.POST, entity, String.class, "");

        ErrorInfo errorInfo = objectMapper.readValue(response.getBody(), ErrorInfo.class);

        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void findPair_giftCardBalance_2350() throws IOException {

        int balance = 2350;

        String uri = "demo/findPair/" + balance;

        LinkedMultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
        parameters.add("file", new ClassPathResource("products.csv"));

        HttpEntity<LinkedMultiValueMap<String, Object>> entity = new HttpEntity<>(parameters, httpHeaders);

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort(uri), HttpMethod.POST, entity, String.class, "");

        List<Product> products = objectMapper.readValue(response.getBody(), new TypeReference<List<Product>>(){});

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(products.size() == 2);
        assertTrue((products.get(0).getPrice() + products.get(1).getPrice()) <= balance);
    }

    @Test
    public void findPair_giftCardBalance_2400() throws IOException {

        int balance = 2400;

        String uri = "demo/findPair/" + balance;

        LinkedMultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
        parameters.add("file", new ClassPathResource("products.csv"));

        HttpEntity<LinkedMultiValueMap<String, Object>> entity = new HttpEntity<>(parameters, httpHeaders);

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort(uri), HttpMethod.POST, entity, String.class, "");

        List<Product> products = objectMapper.readValue(response.getBody(), new TypeReference<List<Product>>(){});

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(products.size() == 2);
        assertTrue((products.get(0).getPrice() + products.get(1).getPrice()) <= balance);
    }

    @Test
    public void findPair_giftCardBalance_5000() throws IOException {

        int balance = 5000;

        String uri = "demo/findPair/" + balance;

        LinkedMultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
        parameters.add("file", new ClassPathResource("products.csv"));

        HttpEntity<LinkedMultiValueMap<String, Object>> entity = new HttpEntity<>(parameters, httpHeaders);

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort(uri), HttpMethod.POST, entity, String.class, "");

        List<Product> products = objectMapper.readValue(response.getBody(), new TypeReference<List<Product>>(){});

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(products.size() == 2);
        assertTrue((products.get(0).getPrice() + products.get(1).getPrice()) <= balance);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}