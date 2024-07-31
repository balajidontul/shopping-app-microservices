package com.personal.service;

import com.personal.Exception.ProductServiceCustomException;
import com.personal.entity.Product;
import com.personal.model.ProductRequest;
import com.personal.model.ProductResponse;
import com.personal.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.beans.BeanUtils.*;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public long addProduct(ProductRequest productRequest) {
        log.info("Adding product");

        Product product = Product.builder()
                .productName(productRequest.getProductName())
                .productQuantity(productRequest.getProductQuantity())
                .productPrice(productRequest.getProductPrice()).build();

        log.info("product created");

        productRepository.save(product);

        return product.getProductId();
    }

    @Override
    public ProductResponse getProductByproductId(long productId) {
        log.info("get product by product id");

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductServiceCustomException("Product with given id not found","PRODUCT_NOT_FOUND"));

        ProductResponse productResponse = new ProductResponse();
        copyProperties(product, productResponse);
        return productResponse;

    }

    @Override
    @Transactional
    public void reduceQuantity(long productId, long quantity) {
        log.info("Reduce quantity {},{}", quantity, productId);

        Product product = productRepository.findById(productId).orElseThrow(() ->
                new ProductServiceCustomException("Product with given Id not found", "PRODUCT_NOT_FOUND"));

        if (product.getProductQuantity() < quantity){
            log.info("Product does not have suffiecient quantity");
            throw new ProductServiceCustomException("Product does not have suffiecient quantity", "INSUFFICIENT_QUANTITY");
        }else {
            product.setProductQuantity(product.getProductQuantity() - quantity);
        }

        productRepository.save(product);

        log.info("product Quantity updated");
    }
}
