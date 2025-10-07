package com.example.notizapp.controller;

import com.example.notizapp.model.Note;
import com.example.notizapp.model.Category;
import com.example.notizapp.service.NoteService;
import com.example.notizapp.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/notes")
public class NoteController {
    private final NoteService noteService;
    private final CategoryService categoryService;

    public NoteController(NoteService noteService, CategoryService categoryService){
        this.noteService = noteService;
        this.categoryService = categoryService;
    }
    @GetMapping
    public String listNotes(@RequestParam(required = false) Long categoryId, Model model){
        List<Note> notes = (categoryId != null)
                ? noteService.getNotesByCategory(categoryId)
                : noteService.getAllNotes();
        List<Category> categories = categoryService.getAllCategories();

        model.addAttribute("notes", notes);
        model.addAttribute("categories", categories);
        model.addAttribute("selectedCategoryId",categoryId);

        return "notes";
    }
    @GetMapping("/new")
    public String showCreateForm(Model model){
        model.addAttribute("note", new Note());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "note-form";
    }
    @PostMapping("/save")
    public String saveNote(@ModelAttribute Note note) {
        if (note.getCategory() != null && note.getCategory().getId() != null) {
            Category category = categoryService.getCategoryById(note.getCategory().getId());
            note.setCategory(category);
        }
        noteService.saveNote(note);
        return "redirect:/notes";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Note note = noteService.getNoteById(id);
        model.addAttribute("note", note);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "note-form";
    }
    @GetMapping("/delete/{id}")
    public String deleteNote(@PathVariable Long id){
        noteService.deleteNote(id);
        return "redirect:/notes";
    }
}
