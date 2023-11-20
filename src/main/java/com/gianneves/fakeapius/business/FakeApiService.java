package com.gianneves.fakeapius.business;

import com.gianneves.fakeapius.apiv1.dto.ProductDTO;
import com.gianneves.fakeapius.infra.FakeApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FakeApiService {

    @Autowired
    private FakeApiClient fakeApiClient;

    public List<ProductDTO> searchProducts() {
        return fakeApiClient.searchProductsList();
    }
}
