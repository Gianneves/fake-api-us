package com.gianneves.fakeapius.apiv1.dto;

import com.gianneves.fakeapius.business.service.FakeApiService;
import com.gianneves.fakeapius.business.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Tag(name = "fake-api")
public class FakeApiController {

    private final FakeApiService service;
    private final ProductService productService;

    @Operation(summary = "Search API products and Save", method ="POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Search completed successfully"),
            @ApiResponse(responseCode = "500", description = "Error when searching for data"),
    })
    @PostMapping("/api")
    public ResponseEntity<List<ProductDTO>> saveApiProducts(){
        return ResponseEntity.ok(service.searchProducts());
    }

    @Operation(summary = "Save new products", method ="POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product saved successfully"),
            @ApiResponse(responseCode = "500", description = "Error saving products"),
    })
    @PostMapping("/")
    public ResponseEntity<ProductDTO> saveProducts(@RequestBody ProductDTO dto){
        return ResponseEntity.ok(productService.saveProductDTO(dto));
    }

    @Operation(summary = "Update new products", method ="PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product deleted successfully"),
            @ApiResponse(responseCode = "500", description = "Error when deleting products"),
    })
    @PutMapping("/")
    public ResponseEntity<ProductDTO> updateProducts(@RequestParam ("id") String id, @RequestBody ProductDTO dto){
        return ResponseEntity.ok(productService.productUpdate(id, dto));
    }

    @Operation(summary = "Deletes products", method ="DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product deleted successfully"),
            @ApiResponse(responseCode = "500", description = "Error when deleting products"),
    })
    @DeleteMapping("/")
    public ResponseEntity<Void> deleteProduct(@RequestParam ("name") String name){
        productService.deleteProduct(name);
        return ResponseEntity.accepted().build();
    }

    @Operation(summary = "Search all products", method ="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product saved successfully"),
            @ApiResponse(responseCode = "500", description = "Error saving products"),
    })
    @GetMapping("/")
    public ResponseEntity<List<ProductDTO>> searchAllProducts(){

        return ResponseEntity.ok(productService.searchAllProducts());
    }

    @Operation(summary = "Search product by name", method ="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product saved successfully"),
            @ApiResponse(responseCode = "500", description = "Error saving products"),
    })
    @GetMapping("/{name}")
    public ResponseEntity<ProductDTO> searchByName(@PathVariable ("name") String name){

        return ResponseEntity.ok(productService.searchByName(name));
    }






}