package com.nemo.server.api.controller.category;

import com.nemo.server.api.ApiResponse;
import com.nemo.server.api.controller.category.request.AddCategoryRequest;
import com.nemo.server.api.controller.category.request.EditCategoryRequest;
import com.nemo.server.api.controller.category.response.CategoryResponse;
import com.nemo.server.api.service.category.CategoryService;
import com.nemo.server.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/add")
    public ApiResponse<CategoryResponse> add(@RequestBody AddCategoryRequest request) {
        log.debug("CategoryController.add");
        log.debug("AddCategoryRequest={}", request);
        CategoryResponse response = categoryService.add(request.toAddCategoryDto());
        log.debug("CategoryResponse={}", response);
        return ApiResponse.ok(response);
    }

    @GetMapping
    public ApiResponse<List<CategoryResponse>> gets() {
        log.debug("CategoryController.gets");
        String email = SecurityUtil.getCurrentLoginId();
        log.debug("email={}", email);
        List<CategoryResponse> responses = categoryService.gets(email);
        log.debug("response size={}", responses.size());
        return ApiResponse.ok(responses);
    }

    @GetMapping("/{categoryId}")
    public ApiResponse<CategoryResponse> get(@PathVariable Long categoryId) {
        log.debug("CategoryController.get");
        CategoryResponse response = categoryService.get(categoryId);
        log.debug("response={}", response);
        return ApiResponse.ok(response);
    }

    @PostMapping("/{categoryId}/edit")
    public ApiResponse<CategoryResponse> edit(@RequestBody EditCategoryRequest request, @PathVariable Long categoryId) {
        log.debug("CategoryController.edit");
        CategoryResponse response = categoryService.edit(categoryId, request.toEditCategoryDto());
        log.debug("response={}", response);
        return ApiResponse.ok(response);
    }

    @PostMapping("/{categoryId}/remove")
    public ApiResponse<?> remove(@PathVariable Long categoryId) {
        log.debug("CategoryController.remove");
        categoryService.remove(categoryId);
        log.debug("remove success");
        return ApiResponse.ok(null);
    }
}
