package com.gianneves.fakeapius.business.service;

import com.gianneves.fakeapius.apiv1.dto.ProductDTO;
import com.gianneves.fakeapius.business.converter.ProductConverter;
import com.gianneves.fakeapius.infra.client.FakeApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class FakeApiService {

    @Autowired
    private FakeApiClient fakeApiClient;

    @Autowired
    private ProductConverter converter;

    @Autowired
    private ProductService service;

    public List<ProductDTO> searchProducts() {
        try {
            List<ProductDTO> dto = fakeApiClient.searchProductsList();
            dto.forEach(product -> {
                Boolean existsName = service.existsByName(product.getName());
                if (existsName.equals(false)) {
                    service.saveProduct(converter.toEntity(product));
                }
            });
            return service.searchAllProducts();
        } catch (Exception e) {
            throw  new RuntimeException("Error to save product!");
        }
    }

}
