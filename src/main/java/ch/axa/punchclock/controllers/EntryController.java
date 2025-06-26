package ch.axa.punchclock.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ch.axa.punchclock.models.Entry;
import ch.axa.punchclock.repositories.EntryRepository;
import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@Controller
@Tag(name = "Entry Web Controller", description = "Web controller for entry management (Thymeleaf views)")
public class EntryController {

    @Autowired
    public EntryRepository entryRepository;

    @GetMapping("/")
    @Operation(summary = "Show entry list", description = "Displays a list of all entries in the web UI.")
    public String showEntryList(Model model) {
        model.addAttribute("entries", entryRepository.findAll());
        return "index";
    }

    @PostMapping("/create")
    @Operation(summary = "Create entry (web)", description = "Creates a new entry via the web form.")
    public String create(@Valid Entry entry, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add";
        }
        entryRepository.save(entry);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    @Operation(summary = "Edit entry form", description = "Displays the edit form for an entry.")
    public String edit(@Parameter(description = "ID of the entry to edit") @PathVariable("id") long id, Model model) {
        Entry entry = entryRepository.findById(id).get();
        model.addAttribute("entry", entry);
        return "edit";
    }

    @PostMapping("/update/{id}")
    @Operation(summary = "Update entry (web)", description = "Updates an entry via the web form.")
    public String update(@Parameter(description = "ID of the entry to update") @PathVariable("id") long id, @Valid Entry entry, BindingResult result) {
         if (result.hasErrors()) {
            entry.setId(id);
            return "edit";
        }

        entryRepository.save(entry);
        
        return "redirect:/";
    }

    @GetMapping("/add")
    @Operation(summary = "Add entry form", description = "Displays the add entry form.")
    public String addEntry(Model model) {
        model.addAttribute("entry", new ch.axa.punchclock.models.Entry());
        return "add";
    }

    @GetMapping("/delete/{id}")
    @Operation(summary = "Delete entry (web)", description = "Deletes an entry via the web UI.")
    public String delete(@Parameter(description = "ID of the entry to delete") @PathVariable("id") long id, Model model) {
        Entry entry = entryRepository.findById(id).get();
        entryRepository.delete(entry);
        return "redirect:/";
    }
}