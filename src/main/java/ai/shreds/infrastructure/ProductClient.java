package ai.shreds.infrastructure;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service", configuration = ProductClientConfig.class)
public interface ProductClient {
    @GetMapping("/product/{id}")
    boolean checkProductExists(@PathVariable("id") String productId);
}