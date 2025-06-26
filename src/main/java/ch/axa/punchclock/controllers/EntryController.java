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


 
@Controller
public class EntryController {
 
    @Autowired
    public EntryRepository entryRepository;
 
    @GetMapping("/")
    public String showEntryList(Model model) {
        model.addAttribute("entries", entryRepository.findAll());
        return "index";
    }

    @PostMapping("/create")
    public String create(@Valid Entry entry, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add";
        }
        entryRepository.save(entry);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model) {
        Entry entry = entryRepository.findById(id).get();
        model.addAttribute("entry", entry);
        return "edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") long id, @Valid Entry entry, BindingResult result) {
         if (result.hasErrors()) {
            entry.setId(id);
            return "edit";
        }

        entryRepository.save(entry);
        
        return "redirect:/";
    }

    @GetMapping("/add")
    public String addEntry(Model model) {
        model.addAttribute("entry", new ch.axa.punchclock.models.Entry());
        return "add";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id, Model model) {
        Entry entry = entryRepository.findById(id).get();
        entryRepository.delete(entry);
        return "redirect:/";

    }
}