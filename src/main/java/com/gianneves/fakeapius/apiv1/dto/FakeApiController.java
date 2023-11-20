package com.gianneves.fakeapius.apiv1.dto;

import com.gianneves.fakeapius.business.FakeApiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Tag(name = "fake-api")
public class FakeApiController {

    @Autowired
    private FakeApiService fakeApiService;

    @Operation(summary = "Search all products", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Search completed successfully"),
            @ApiResponse(responseCode = "422", description = "Invalid request data"),
            @ApiResponse(responseCode = "400", description = "Invalid parameters"),
            @ApiResponse(responseCode = "500", description = "Error when fetching data")
    })
    @GetMapping
    public ResponseEntity<List<ProductDTO>> searchProducts() {
        return ResponseEntity.ok().body(fakeApiService.searchProducts());
    }

}
