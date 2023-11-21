package com.gianneves.fakeapius.infra.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity(name = "ProductEntity")
@Table(name = "products")
@Getter
@Setter
@RequiredArgsConstructor
@Builder
public class ProductEntity {

    @Id
    @Column(name = "id")
    private String id;

    @JsonProperty(value = "title")
    private String name;
    @JsonProperty(value = "price")
    private BigDecimal price;
    @JsonProperty(value = "category")
    private String category;
    @JsonProperty(value = "description")
    private String description;
    @JsonProperty(value = "image")
    private String image;
}
