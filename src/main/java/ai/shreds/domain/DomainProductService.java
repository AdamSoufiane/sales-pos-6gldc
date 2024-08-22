package ai.shreds.domain;

import ai.shreds.domain.exception.CategoryNotFoundException;
import ai.shreds.domain.exception.InvalidProductDataException;
import ai.shreds.domain.exception.ProductNotFoundException;
import ai.shreds.domain.port.DomainCategoryRepositoryPort;
import ai.shreds.domain.port.DomainProductRepositoryPort;
import ai.shreds.domain.port.DomainProductServicePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class DomainProductService implements DomainProductServicePort {

    private final DomainProductRepositoryPort productRepository;
    private final DomainCategoryRepositoryPort categoryRepository;

    @Override
    public void save(@NotNull DomainProductEntity product) {
        validateProductData(product);
        checkCategoryExists(product.getCategoryId());
        productRepository.save(product);
    }

    @Override
    public Optional<DomainProductEntity> findById(@NotNull UUID id) {
        return productRepository.findById(id);
    }

    @Override
    public void deleteById(@NotNull UUID id) {
        Optional<DomainProductEntity> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.deleteById(id);
        } else {
            throw new ProductNotFoundException("Product not found");
        }
    }

    private void validateProductData(@NotNull DomainProductEntity product) {
        if (product.getName() == null || product.getName().isEmpty()) {
            throw new InvalidProductDataException("Product name must not be empty");
        }
        if (product.getPrice() == null || product.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidProductDataException("Product price must be a positive value");
        }
        if (product.getDescription() == null || product.getDescription().isEmpty()) {
            throw new InvalidProductDataException("Product description must not be empty");
        }
        if (product.getCategoryId() == null) {
            throw new InvalidProductDataException("Product category ID must not be null");
        }
    }

    private void checkCategoryExists(@NotNull UUID categoryId) {
        if (!categoryRepository.findById(categoryId).isPresent()) {
            throw new CategoryNotFoundException("Category does not exist");
        }
    }
}