package com.gianneves.fakeapius.business.service;

import com.gianneves.fakeapius.apiv1.dto.ProductDTO;
import com.gianneves.fakeapius.business.converter.ProductConverter;
import com.gianneves.fakeapius.infra.entities.ProductEntity;
import com.gianneves.fakeapius.infra.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductConverter converter;

    public ProductEntity saveProduct(ProductEntity product) {
        try {
            return repository.save(product);
        } catch (Exception e) {
            throw new RuntimeException("Error when saving products!", e);
        }
    }

    public ProductDTO saveProductDTO(ProductDTO dto) {
        try {
            ProductEntity entity = converter.toEntity(dto);
            return converter.toDto(repository.save(entity));
        } catch (Exception e) {
            throw new RuntimeException("Error when saving products!", e);
        }
    }


    public List<ProductDTO> searchAllProducts() {
        try {
            return converter.dtoList(repository.findAll());
        } catch (Exception e) {
            throw new RuntimeException("Error when saving products!", e);
        }
    }

    public ProductDTO searchByName(String name) {
        try {
            return converter.toDto(repository.findByName(name));
        } catch (Exception e) {
            throw new RuntimeException("Product not found!", e);
        }
    }

    public void deleteProduct(String name) {
        try {
            repository.deleteByName(name);
        } catch (Exception e) {
            throw new RuntimeException("error when deleting!", e);
        }
    }

    public boolean existsByName(String name) {
        try {
            return repository.existsByName(name);
        } catch (Exception e) {
            throw new RuntimeException("Product not found!", e);
        }
    }

    public ProductDTO productUpdate(String id, ProductDTO productDTO) {
        try {
            ProductEntity entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Id do not exists"));
            saveProduct(converter.toEntityUpdate(entity, productDTO, id));
            return converter.toDto(repository.findByName(entity.getName()));
        } catch (Exception e) {
            throw new RuntimeException("Product has not updated!", e);
        }
    }
}
