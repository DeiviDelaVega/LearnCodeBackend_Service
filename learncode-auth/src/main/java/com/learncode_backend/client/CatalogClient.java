package com.learncode_backend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("CATALOG-SERVICE")
public interface CatalogClient {

    @GetMapping("/api/catalog/count")
    long countCourses();
}
