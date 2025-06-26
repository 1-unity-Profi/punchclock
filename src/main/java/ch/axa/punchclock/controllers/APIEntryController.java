package ch.axa.punchclock.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ch.axa.punchclock.models.Entry;
import ch.axa.punchclock.repositories.EntryRepository;
import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/entries")
@Tag(name = "Entry API", description = "API for managing entries")
public class APIEntryController {

  @Autowired
  private EntryRepository entryRepository;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Create a new entry", description = "Creates a new entry and returns the created entity.")
  public Entry create(@RequestBody @Valid Entry entry) {
    return entryRepository.save(entry);
  }

  @GetMapping
  @Operation(summary = "Get entries", description = "Returns a list of entries, optionally filtered by category, tag, or description.")
  public Iterable<Entry> index(
    @Parameter(description = "Category ID to filter entries") @RequestParam(required = false) Long categoryId,
    @Parameter(description = "Tag ID to filter entries") @RequestParam(required = false) Long tagId,
    @Parameter(description = "Search in description") @RequestParam(required = false) String searchDescription) {
    if (categoryId != null) {
      return entryRepository.findByCategory(categoryId);
    } else if(tagId != null) {
      return entryRepository.findByTags_Id(tagId);
    } else if(searchDescription != null) {
      return entryRepository.findByDescriptionWithCriteria(searchDescription);
    } else {
      return entryRepository.findAll();
    }
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get entry by ID", description = "Returns an entry by its ID.")
  public ResponseEntity<Entry> read(@Parameter(description = "ID of the entry to retrieve") @PathVariable Long id) {
    return ResponseEntity.of(entryRepository.findById(id));
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update an entry", description = "Updates an existing entry by its ID.")
  public Entry update(@Parameter(description = "ID of the entry to update") @PathVariable Long id, @RequestBody @Valid Entry entry) {
    entry.setId(id);
    return entryRepository.save(entry);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete an entry", description = "Deletes an entry by its ID.")
  public ResponseEntity<Entry> delete(@Parameter(description = "ID of the entry to delete") @PathVariable Long id) {
    var entry = entryRepository.findById(id);
    if(entry.isPresent()) {
      entryRepository.delete(entry.get());
      return new ResponseEntity<>(HttpStatus.OK);
    }

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}