package com.gianneves.fakeapius.infra.repositories;

import com.gianneves.fakeapius.infra.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {

    boolean existsByName(String name);
    ProductEntity findByName(String name);

    void deleteByName(String name);
}
