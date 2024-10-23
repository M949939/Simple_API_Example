package MSI.example.Framework_Module.controller;

import MSI.example.Framework_Module.entity.Category;
import MSI.example.Framework_Module.exception.ResourceNotFoundException;
import MSI.example.Framework_Module.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        List<Category> existingCategories = categoryRepository.findByName(category.getName());
        if (!existingCategories.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        Category newCategory = categoryRepository.save(category);
        return ResponseEntity.ok(newCategory);
    }

    @GetMapping("/{id:\\d+}")  // This regex ensures that the id must be digits only
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        return ResponseEntity.ok(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category categoryDetails) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        category.setName(categoryDetails.getName());
        Category updatedCategory = categoryRepository.save(category);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        categoryRepository.delete(category);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteAllCategories")
    public ResponseEntity<Void> deleteAllCategories() {
        categoryRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
