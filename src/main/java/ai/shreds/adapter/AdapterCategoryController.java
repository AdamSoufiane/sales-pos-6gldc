package ai.shreds.adapter; 
  
 import ai.shreds.application.ApplicationCategoryServicePort; 
 import ai.shreds.shared.AdapterCategoryRequestParams; 
 import ai.shreds.shared.AdapterCategoryResponseDTO; 
 import ai.shreds.shared.DomainCategoryEntity; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.data.domain.Page; 
 import org.springframework.data.domain.PageRequest; 
 import org.springframework.data.domain.Pageable; 
 import org.springframework.http.ResponseEntity; 
 import org.springframework.web.bind.annotation.ExceptionHandler; 
 import org.springframework.web.bind.annotation.GetMapping; 
 import org.springframework.web.bind.annotation.PathVariable; 
 import org.springframework.web.bind.annotation.RequestMapping; 
 import org.springframework.web.bind.annotation.RequestParam; 
 import org.springframework.web.bind.annotation.RestController; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
 import java.util.List; 
 import java.util.Map; 
 import java.util.stream.Collectors; 
  
 @RestController 
 @RequestMapping("/categories") 
 public class AdapterCategoryController { 
  
     private static final Logger logger = LoggerFactory.getLogger(AdapterCategoryController.class); 
  
     @Autowired 
     private ApplicationCategoryServicePort categoryService; 
  
     @Autowired 
     private AdapterCategoryMapper categoryMapper; 
  
     @GetMapping 
     public ResponseEntity<List<AdapterCategoryResponseDTO>> getAllCategories(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) { 
         try { 
             logger.info("Fetching all categories"); 
             Pageable pageable = PageRequest.of(page, size); 
             Page<DomainCategoryEntity> categoriesPage = categoryService.getAllCategories(pageable); 
             List<AdapterCategoryResponseDTO> response = categoriesPage.getContent().stream() 
                     .map(categoryMapper::toResponseDTO) 
                     .collect(Collectors.toList()); 
             return ResponseEntity.ok(response); 
         } catch (Exception e) { 
             logger.error("Error fetching all categories", e); 
             return ResponseEntity.status(500).body(null); 
         } 
     } 
  
     @GetMapping("/{id}") 
     public ResponseEntity<AdapterCategoryResponseDTO> getCategoryById(@PathVariable Long id) { 
         try { 
             if (id <= 0) { 
                 return ResponseEntity.badRequest().body(null); 
             } 
             logger.info("Fetching category with ID: {}", id); 
             DomainCategoryEntity category = categoryService.getCategoryById(id); 
             AdapterCategoryResponseDTO response = categoryMapper.toResponseDTO(category); 
             return ResponseEntity.ok(response); 
         } catch (Exception e) { 
             logger.error("Error fetching category with ID: {}", id, e); 
             return ResponseEntity.status(500).body(null); 
         } 
     } 
  
     @GetMapping("/search") 
     public ResponseEntity<List<AdapterCategoryResponseDTO>> searchCategories(@RequestParam Map<String, String> params, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) { 
         try { 
             logger.info("Searching categories with params: {}", params); 
             // Validate input parameters 
             if (params.containsKey("category_id")) { 
                 try { 
                     Long.parseLong(params.get("category_id")); 
                 } catch (NumberFormatException e) { 
                     return ResponseEntity.badRequest().body(null); 
                 } 
             } 
             Pageable pageable = PageRequest.of(page, size); 
             Page<DomainCategoryEntity> categoriesPage = categoryService.searchCategories(params, pageable); 
             List<AdapterCategoryResponseDTO> response = categoriesPage.getContent().stream() 
                     .map(categoryMapper::toResponseDTO) 
                     .collect(Collectors.toList()); 
             return ResponseEntity.ok(response); 
         } catch (Exception e) { 
             logger.error("Error searching categories", e); 
             return ResponseEntity.status(500).body(null); 
         } 
     } 
  
     @ExceptionHandler(Exception.class) 
     public ResponseEntity<String> handleException(Exception e) { 
         logger.error("Unhandled exception", e); 
         return ResponseEntity.status(500).body("Internal Server Error"); 
     } 
 }