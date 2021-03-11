package com.example.demo.service;

import com.example.demo.model.Product;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ValidationException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class DemoServiceImpl implements DemoService {

    private final static Logger log = Logger.getLogger(DemoServiceImpl.class);

    public List<Product> findPair(Integer cardBalance, MultipartFile multipartCsvFile) throws IOException {

        BigDecimal giftCardBalanceInDollars = BigDecimal.valueOf(cardBalance).divide(new BigDecimal(100));

        log.debug("Attempting to find the closest pair of products for a gift card balance of: $" + giftCardBalanceInDollars );

        List<Product> products = getProductsFromCsv(multipartCsvFile);
        List<Product> productPair = new ArrayList<>();

        int leftResult = 0;
        int rightResult = 0;
        int left = 0;
        int right = products.size() - 1;
        int diff = Integer.MAX_VALUE;

        if (products.get(0).getPrice() + products.get(1).getPrice() > cardBalance) {
            throw new ValidationException("Not possible");
        }

        while (right > 1) {

            if (Math.abs(products.get(left).getPrice() + products.get(right).getPrice() - cardBalance) < diff) {
                leftResult = left;
                rightResult = right;
                diff = Math.abs(products.get(left).getPrice() + products.get(right).getPrice() - cardBalance);
            }

            if (products.get(left).getPrice() + products.get(right).getPrice() > cardBalance) {
                right --;
            } else {
                left++;
            }

            if (left == right) {
                leftResult = left;
                rightResult = left - 1;
                break;
            }
        }

        String product1Name = products.get(leftResult).getName();
        int product1Amount = products.get(leftResult).getPrice();
        BigDecimal product1AmountInDollars = BigDecimal.valueOf(product1Amount).divide(new BigDecimal(100));
        productPair.add(products.get(leftResult));

        String product2Name = products.get(rightResult).getName();
        int product2Amount = products.get(rightResult).getPrice();
        BigDecimal product2AmountInDollars = BigDecimal.valueOf(product2Amount).divide(new BigDecimal(100));
        productPair.add(products.get(rightResult));

        log.debug("The closest pair for a balance of $" + giftCardBalanceInDollars + " is " + product1Name + "($" + product1AmountInDollars + ") and " + product2Name + "($" + product2AmountInDollars + ")");

        return productPair;

    }

    private List<Product> getProductsFromCsv(MultipartFile multipartCsvFile) throws IOException {

        String fileName = "input/" + multipartCsvFile.getOriginalFilename();

        File csvFile = new File(fileName);
        FileUtils.writeByteArrayToFile(csvFile, multipartCsvFile.getBytes());

        HeaderColumnNameMappingStrategy<Product> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(Product.class);

        CsvToBean<Product> csvProducts = new CsvToBean<>();
        csvProducts.setMappingStrategy(strategy);
        csvProducts.setCsvReader(new CSVReader(new FileReader(fileName)));

        List<Product> products = csvProducts.parse();
        products.sort(Comparator.comparing(Product::getPrice));

        return products;
    }
}