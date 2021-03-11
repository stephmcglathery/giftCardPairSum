package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.DemoService;
import org.easymock.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(EasyMockRunner.class)
public class DemoControllerTest extends EasyMockSupport {

    @TestSubject
    DemoController demoController = new DemoController();

    @Mock
    DemoService demoService;

    @Mock
    MultipartFile multipartFile;

    @Before
    public void setUp() {
    }

    @Test
    public void findPair() throws Exception {

        Product product = new Product();

        List<Product> products = new ArrayList<>();
        products.add(product);

        demoService.findPair(5000, multipartFile);
        EasyMock.expectLastCall().andReturn(products);
        replayAll();

        List<Product> actualResult = demoController.findPair(5000, multipartFile);
        assertTrue(actualResult.size() > 0);
        verifyAll();
    }
}