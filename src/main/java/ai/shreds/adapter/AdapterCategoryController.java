package ai.shreds.adapter;

import ai.shreds.application.ApplicationCategoryServicePort;
import ai.shreds.domain.DomainCategoryEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
public class AdapterCategoryController {

    private static final Logger logger = LoggerFactory.getLogger(AdapterCategoryController.class);

    private final ApplicationCategoryServicePort categoryService;
    private final AdapterCategoryMapper categoryMapper;

    public AdapterCategoryController(ApplicationCategoryServicePort categoryService, AdapterCategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @GetMapping
    public ResponseEntity<List<AdapterCategoryResponseDTO>> getAllCategories(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        if (page < 0 || size < 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        try {
            logger.info("Fetching all categories");
            List<DomainCategoryEntity> categories = categoryService.getAllCategories();
            List<AdapterCategoryResponseDTO> response = categories.stream()
                    .map(categoryMapper::toResponseDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error fetching all categories", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdapterCategoryResponseDTO> getCategoryById(@PathVariable @NotNull Long id) {
        if (id <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        try {
            logger.info("Fetching category with ID: {}", id);
            DomainCategoryEntity category = categoryService.getCategoryById(id);
            AdapterCategoryResponseDTO response = categoryMapper.toResponseDTO(category);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error fetching category with ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<AdapterCategoryResponseDTO>> searchCategories(@Valid AdapterCategoryRequestParams params, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        if (page < 0 || size < 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        try {
            logger.info("Searching categories with params: {}", params);
            Map<String, String> queryParams = Map.of(
                "name", params.getName() != null ? params.getName() : "",
                "categoryId", params.getCategoryId() != null ? String.valueOf(params.getCategoryId()) : "",
                "createdAfter", params.getCreatedAfter() != null ? String.valueOf(params.getCreatedAfter().getTime()) : "",
                "updatedAfter", params.getUpdatedAfter() != null ? String.valueOf(params.getUpdatedAfter().getTime()) : ""
            );
            List<DomainCategoryEntity> categories = categoryService.searchCategories(queryParams);
            List<AdapterCategoryResponseDTO> response = categories.stream()
                    .map(categoryMapper::toResponseDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error searching categories", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        logger.error("Unhandled exception", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
    }
}