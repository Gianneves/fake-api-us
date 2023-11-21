package com.gianneves.fakeapius.infra.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "ProductEntity")
@Table(name = "products")
@Getter
@Setter

@Builder
public class ProductEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "title", length = 800)
    private String name;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "category", length = 800)
    private String category;
    @Column(name = "description", length = 800)
    private String description;
    @Column(name = "image", length = 800)
    private String image;
    @Column(name = "inclusion_date")
    private LocalDateTime inclusionDate;
    @Column(name = "update_date")
    private LocalDateTime updateDate;
}
