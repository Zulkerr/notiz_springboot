package com.example.notizapp.service;

import com.example.notizapp.model.Note;
import com.example.notizapp.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository){
        this.noteRepository = noteRepository;
    }
    public List<Note> getAllNotes(){
        return noteRepository.findAll();
    }
    public List<Note>getNotesByCategory(Long categoryId) {
        return noteRepository.findByCategoryId(categoryId);
    }
    public Note saveNote(Note note) {
        return noteRepository.save(note);
    }
    public void deleteNote(Long id){
        noteRepository.deleteById(id);
    }
    public Note getNoteById(Long id){
        return noteRepository.findById(id).orElse(null);
    }
}
