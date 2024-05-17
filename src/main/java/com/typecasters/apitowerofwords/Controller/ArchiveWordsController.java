package com.typecasters.apitowerofwords.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.typecasters.apitowerofwords.Entity.ArchiveWordsEntity;
import com.typecasters.apitowerofwords.Service.ArchiveWordsService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/archive_words")
public class ArchiveWordsController {
    @Autowired
    ArchiveWordsService archiveWordsService;

    // Create
    @PostMapping("/insert/{userID}/word/{word}/")
    public ResponseEntity<String> insertArchiveWords(@PathVariable int userID ,@PathVariable String word) {
        try{
            ArchiveWordsEntity insertedWord = archiveWordsService.insertArchiveWords(userID, word);
            return new ResponseEntity<>("Word Archived!", HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException | NullPointerException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Read
    @GetMapping("/view/{userID}")
    public ResponseEntity<List<ArchiveWordsEntity>> viewAllArchiveWords(@PathVariable int userID) {
        try{
            List<ArchiveWordsEntity> words = archiveWordsService.viewAllArchiveWords(userID);
            return new ResponseEntity<>(words, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException | NullPointerException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/view_by_id/{archiveWordsID}")
    public ResponseEntity<ArchiveWordsEntity> viewArchiveWordsByID(@PathVariable int archiveWordsID) {
        try{
            Optional<ArchiveWordsEntity> word = archiveWordsService.viewArchiveWordsByID(archiveWordsID);
            return word.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException | NullPointerException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update
    @PutMapping("/edit")
    public ResponseEntity<ArchiveWordsEntity> editArchiveWords(@RequestBody ArchiveWordsEntity word) {
        try{
            ArchiveWordsEntity updatedWord = archiveWordsService.editArchiveWords(word);
            return new ResponseEntity<>(updatedWord, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException | NullPointerException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete
    @PutMapping("/remove/{archiveWordsID}")
    public ResponseEntity<String> removeArchiveWords(@PathVariable int archiveWordsID) {
        try{
            ArchiveWordsEntity removedWord = archiveWordsService.removeArchiveWords(archiveWordsID);
            return new ResponseEntity<>("Word Removed!", HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException | NullPointerException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
