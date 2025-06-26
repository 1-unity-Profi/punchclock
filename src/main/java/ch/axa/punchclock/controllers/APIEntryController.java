package ch.axa.punchclock.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ch.axa.punchclock.models.Entry;
import ch.axa.punchclock.repositories.EntryRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/entries")
public class APIEntryController {

  @Autowired
  private EntryRepository entryRepository;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Entry create(@RequestBody @Valid Entry entry) {
    return entryRepository.save(entry);
  }

  @GetMapping
  public Iterable<Entry> index(@RequestParam(required = false) Long categoryId, @RequestParam(required = false) Long tagId, @RequestParam(required = false) String searchDescription) {
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
  public ResponseEntity<Entry> read(@PathVariable Long id) {
    return ResponseEntity.of(entryRepository.findById(id));
  }

  @PutMapping("/{id}")
  public Entry update(@PathVariable Long id, @RequestBody @Valid Entry entry) {
    entry.setId(id);
    return entryRepository.save(entry);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Entry> delete(@PathVariable Long id) {
    var entry = entryRepository.findById(id);
    if(entry.isPresent()) {
      entryRepository.delete(entry.get());
      return new ResponseEntity<>(HttpStatus.OK);
    }

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}