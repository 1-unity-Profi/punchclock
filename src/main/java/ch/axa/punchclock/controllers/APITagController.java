package ch.axa.punchclock.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ch.axa.punchclock.models.Tag;
import ch.axa.punchclock.repositories.TagRepository;
import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/tags")
@io.swagger.v3.oas.annotations.tags.Tag(name = "Tag API", description = "API for managing tags")
public class APITagController {

  @Autowired
  private TagRepository tagRepository;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Create a new tag", description = "Creates a new tag and returns the created entity.")
  public Tag create(@RequestBody @Valid Tag tag) {
    return tagRepository.save(tag);
  }

  @GetMapping
  @Operation(summary = "Get all tags", description = "Returns a list of all tags.")
  public Iterable<Tag> index() {
    return tagRepository.findAll();
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get tag by ID", description = "Returns a tag by its ID.")
  public ResponseEntity<Tag> read(@Parameter(description = "ID of the tag to retrieve") @PathVariable Long id) {
    return ResponseEntity.of(tagRepository.findById(id));
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update a tag", description = "Updates an existing tag by its ID.")
  public Tag update(@Parameter(description = "ID of the tag to update") @PathVariable Long id, @RequestBody @Valid Tag tag) {
    tag.setId(id);
    return tagRepository.save(tag);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete a tag", description = "Deletes a tag by its ID.")
  public ResponseEntity<Tag> delete(@Parameter(description = "ID of the tag to delete") @PathVariable Long id) {
    var tag = tagRepository.findById(id);
    if(tag.isPresent()) {
      tagRepository.delete(tag.get());
      return new ResponseEntity<>(HttpStatus.OK);
    }

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
