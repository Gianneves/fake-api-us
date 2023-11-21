package com.gianneves.fakeapius.business.converter;

import com.gianneves.fakeapius.apiv1.dto.ProductDTO;
import com.gianneves.fakeapius.infra.entities.ProductEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class ProductConverter {

    public ProductEntity toEntity(ProductDTO dto) {
        return ProductEntity.builder()
                .id(String.valueOf(UUID.randomUUID()))
                .name(dto.getName())
                .category(dto.getCategory())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .image(dto.getImage())
                .inclusionDate(LocalDateTime.now())
                .build();
    }

    public ProductEntity toEntityUpdate(ProductEntity entity, ProductDTO dto, String id) {
        return ProductEntity.builder()
                .id(id)
                .name(dto.getName() != null ? dto.getName() : entity.getName())
                .category(dto.getCategory() != null ? dto.getCategory() : entity.getCategory())
                .description(dto.getDescription() != null ? dto.getDescription() : entity.getDescription())
                .price(dto.getPrice() != null ? dto.getPrice() : entity.getPrice())
                .image(dto.getImage() != null ? dto.getImage() : entity.getImage())
                .inclusionDate(entity.getInclusionDate())
                .updateDate(LocalDateTime.now())
                .build();
    }

    public ProductDTO toDto(ProductEntity entity) {
        return ProductDTO.builder()
                .entityId(entity.getId())
                .name(entity.getName())
                .category(entity.getCategory())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .image(entity.getImage())
                .build();

    }

    public List<ProductDTO> dtoList(List<ProductEntity> entities) {
       return entities.stream().map(this::toDto).toList();
    }
}
