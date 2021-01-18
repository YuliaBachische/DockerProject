package com.vironit.project.controller;

import com.vironit.project.exception.ResourceNotFoundException;
import com.vironit.project.model.Word;
import com.vironit.project.repository.WordRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/words")
public class WordController {

    private WordRepository wordRepository;

    public WordController(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @GetMapping
    public ResponseEntity<List<Word>> getAllWords(){
        return ResponseEntity.ok(wordRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Word> getWord(@PathVariable("id") Long id){
        Word word = wordRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Word with id " + id + " not found"));
        return ResponseEntity.ok().body(word);
    }

    @PostMapping
    public Word saveWord(@RequestBody Word word){
        return wordRepository.save(word);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteWord(@PathVariable("id") Long wordId){
        Word word = wordRepository.findById(wordId).orElseThrow( () -> new ResourceNotFoundException("Words with id " + wordId + " not found"));
        wordRepository.delete(word);
        return ResponseEntity.ok().build();
    }


}
