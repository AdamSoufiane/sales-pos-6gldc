package ai.shreds.adapter;

import ai.shreds.application.ApplicationCreateCategoryInputPort;
import ai.shreds.application.ApplicationUpdateCategoryInputPort;
import ai.shreds.application.ApplicationDeleteCategoryInputPort;
import ai.shreds.adapter.AdapterCategoryCreateRequest;
import ai.shreds.adapter.AdapterCategoryUpdateRequest;
import ai.shreds.adapter.AdapterCategoryResponse;
import ai.shreds.adapter.AdapterCategoryMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class AdapterCategoryController {

    private final ApplicationCreateCategoryInputPort createCategoryInputPort;
    private final ApplicationUpdateCategoryInputPort updateCategoryInputPort;
    private final ApplicationDeleteCategoryInputPort deleteCategoryInputPort;
    private final AdapterCategoryMapper categoryMapper;

    @PostMapping
    public ResponseEntity<AdapterCategoryResponse> createCategory(@Valid @RequestBody @Size(min = 1, max = 255) AdapterCategoryCreateRequest request) {
        try {
            AdapterCategoryResponse response = createCategoryInputPort.createCategory(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdapterCategoryResponse> updateCategory(@PathVariable UUID id, @Valid @RequestBody @Size(min = 1, max = 255) AdapterCategoryUpdateRequest request) {
        try {
            AdapterCategoryResponse response = updateCategoryInputPort.updateCategory(id, request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id) {
        try {
            deleteCategoryInputPort.deleteCategory(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return handleException(e);
        }
    }

    private ResponseEntity<Void> handleException(Exception e) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}