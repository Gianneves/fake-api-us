package com.gianneves.fakeapius.apiv1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gianneves.fakeapius.apiv1.dto.FakeApiController;
import com.gianneves.fakeapius.apiv1.dto.ProductDTO;
import com.gianneves.fakeapius.business.service.FakeApiService;
import com.gianneves.fakeapius.business.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class FakeApiControllerTest {

    @InjectMocks
    FakeApiController controller;

    @Mock
    FakeApiService fakeApiService;

    @Mock
    ProductService productService;

    private MockMvc mockMvc;

    private String json;

    private  String url;

    private ProductDTO productDTO;

    private final ObjectMapper objectMapper = new ObjectMapper();
    @BeforeEach
    void setup() throws JsonProcessingException {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).alwaysDo(print()).build();
        url = "/products";
        productDTO = ProductDTO.builder().name("Red Jacket").category("clothes").description("Red rocker jacket 80's style").price(new BigDecimal(500.00)).build();
        json = objectMapper.writeValueAsString(productDTO);
    }
    @Test
    void searchForProductApiAndSaveSuccessfully() throws Exception {

        when(fakeApiService.searchProducts()).thenReturn(Collections.singletonList(productDTO));
        mockMvc.perform(post(url + "/api")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(fakeApiService).searchProducts();
        verifyNoMoreInteractions(fakeApiService);

    }

}
