package com.example.demo.service;

import com.example.demo.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DemoService {

    List<Product> findPair(Integer cardBalance, MultipartFile multipartFile) throws IOException;
}