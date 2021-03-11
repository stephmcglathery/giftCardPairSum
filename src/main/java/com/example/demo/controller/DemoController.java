package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.DemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@EnableAutoConfiguration
@Api(value="Demo Controller")
@RequestMapping(value = "/demo")
public class DemoController extends BaseController{

    @Autowired
    DemoService demoService;

    @ResponseBody
    @PostMapping(path="/findPair/{cardBalance}")
    @ApiOperation(value = "Find pair", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<Product> findPair(
            @ApiParam(value = "Enter the gift card balance in pennies", required = true) @PathVariable("cardBalance") Integer cardBalance,
            @ApiParam(name = "file", value = "Select the csv file to Upload")
            @RequestPart("file") MultipartFile multipartCsvFile) throws IOException {

        return demoService.findPair(cardBalance, multipartCsvFile);
    }
}