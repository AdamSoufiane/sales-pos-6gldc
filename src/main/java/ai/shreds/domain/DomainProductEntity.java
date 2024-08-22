package ai.shreds.domain;

import ai.shreds.adapter.AdapterCategoryDTO;
import ai.shreds.adapter.AdapterProductCreateResponse;
import ai.shreds.adapter.AdapterProductUpdateResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
public class DomainProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @NotNull
    @Positive
    @Column(nullable = false)
    private BigDecimal price;

    @NotNull
    @Column(nullable = false)
    private UUID categoryId;

    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;

    @Column(nullable = false)
    private Timestamp updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }

    public AdapterProductCreateResponse toAdapterProductCreateResponse(AdapterCategoryDTO categoryDTO) {
        AdapterProductCreateResponse response = new AdapterProductCreateResponse();
        response.setProductId(this.id);
        response.setName(this.name);
        response.setDescription(this.description);
        response.setPrice(this.price);
        response.setCategoryId(this.categoryId);
        response.setCreatedAt(this.createdAt.toLocalDateTime());
        response.setUpdatedAt(this.updatedAt.toLocalDateTime());
        response.setCategoryName(categoryDTO.getName());
        return response;
    }

    public AdapterProductUpdateResponse toAdapterProductUpdateResponse(AdapterCategoryDTO categoryDTO) {
        AdapterProductUpdateResponse response = new AdapterProductUpdateResponse();
        response.setProductId(this.id);
        response.setName(this.name);
        response.setDescription(this.description);
        response.setPrice(this.price);
        response.setCategoryId(this.categoryId);
        response.setCreatedAt(this.createdAt);
        response.setUpdatedAt(this.updatedAt);
        response.setCategoryName(categoryDTO.getName());
        return response;
    }
}