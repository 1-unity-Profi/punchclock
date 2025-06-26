package ch.axa.punchclock.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ch.axa.punchclock.models.Category;
import ch.axa.punchclock.repositories.CategoryRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/categories")
public class APICategoryController {

  @Autowired
  private CategoryRepository categoryRepository;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Category create(@RequestBody @Valid Category category) {
    return categoryRepository.save(category);
  }

  @GetMapping
  public Iterable<Category> index(@RequestParam Long categoryId) {
    return categoryRepository.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Category> read(@PathVariable Long id) {
    return ResponseEntity.of(categoryRepository.findById(id));
  }

  @PutMapping("/{id}")
  public Category update(@PathVariable Long id, @RequestBody @Valid Category category) {
    category.setId(id);
    return categoryRepository.save(category);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Category> delete(@PathVariable Long id) {
    var category = categoryRepository.findById(id);
    if(category.isPresent()) {
      categoryRepository.delete(category.get());
      return new ResponseEntity<>(HttpStatus.OK);
    }

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
