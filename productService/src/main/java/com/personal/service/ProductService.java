package com.personal.service;

import com.personal.model.ProductRequest;
import com.personal.model.ProductResponse;

public interface ProductService {
    long addProduct(ProductRequest productRequest);

    ProductResponse getProductByproductId(long productId);

    void reduceQuantity(long productId, long quantity);
}
