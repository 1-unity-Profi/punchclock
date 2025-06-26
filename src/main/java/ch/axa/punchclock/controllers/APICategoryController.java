package ch.axa.punchclock.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ch.axa.punchclock.models.Category;
import ch.axa.punchclock.repositories.CategoryRepository;
import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/categories")
@Tag(name = "Category API", description = "API for managing categories")
public class APICategoryController {

  @Autowired
  private CategoryRepository categoryRepository;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Create a new category", description = "Creates a new category and returns the created entity.")
  public Category create(@RequestBody @Valid Category category) {
    return categoryRepository.save(category);
  }

  @GetMapping
  @Operation(summary = "Get all categories", description = "Returns a list of all categories.")
  public Iterable<Category> index(@Parameter(description = "Category ID (not used)") @RequestParam(required = false) Long categoryId) {
    return categoryRepository.findAll();
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get category by ID", description = "Returns a category by its ID.")
  public ResponseEntity<Category> read(@Parameter(description = "ID of the category to retrieve") @PathVariable Long id) {
    return ResponseEntity.of(categoryRepository.findById(id));
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update a category", description = "Updates an existing category by its ID.")
  public Category update(@Parameter(description = "ID of the category to update") @PathVariable Long id, @RequestBody @Valid Category category) {
    category.setId(id);
    return categoryRepository.save(category);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete a category", description = "Deletes a category by its ID.")
  public ResponseEntity<Category> delete(@Parameter(description = "ID of the category to delete") @PathVariable Long id) {
    var category = categoryRepository.findById(id);
    if(category.isPresent()) {
      categoryRepository.delete(category.get());
      return new ResponseEntity<>(HttpStatus.OK);
    }

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
