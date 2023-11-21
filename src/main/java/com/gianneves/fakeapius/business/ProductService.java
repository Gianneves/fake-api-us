package com.gianneves.fakeapius.business;

import com.gianneves.fakeapius.infra.entities.ProductEntity;
import com.gianneves.fakeapius.infra.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public ProductEntity saveProduct(ProductEntity product) {
        try {
            return repository.save(product);
        } catch (Exception e) {
            throw new RuntimeException("Error when saving products!");
        }
    }
}
