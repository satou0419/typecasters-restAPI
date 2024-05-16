package com.typecasters.apitowerofwords.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.typecasters.apitowerofwords.Entity.ArchiveWordsEntity;
import com.typecasters.apitowerofwords.Service.ArchiveWordsService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/archive_words")
public class ArchiveWordsController {
    @Autowired
    ArchiveWordsService archiveWordsService;

    // Create
    @PostMapping("/insert")
    public ResponseEntity<String> insertArchiveWords(@RequestBody ArchiveWordsEntity word) {
        ArchiveWordsEntity insertedWord = archiveWordsService.insertArchiveWords(word.getUserID(), word);
//        return new ResponseEntity<>("{ \"message\": \"Word Added!\" }", HttpStatus.OK);
        return ResponseEntity.ok("{ \"message\": \"Word Added!\" }");

    }

    // Read
    @GetMapping("/view/{userID}")
    public ResponseEntity<List<ArchiveWordsEntity>> viewAllArchiveWords(@PathVariable int userID) {
        List<ArchiveWordsEntity> words = archiveWordsService.viewAllArchiveWords(userID);
        return new ResponseEntity<>(words, HttpStatus.OK);
    }

    @GetMapping("/view_by_id/{archiveWordsID}")
    public ResponseEntity<ArchiveWordsEntity> viewArchiveWordsByID(@PathVariable int archiveWordsID) {
        Optional<ArchiveWordsEntity> word = archiveWordsService.viewArchiveWordsByID(archiveWordsID);
        return word.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update
    @PutMapping("/edit")
    public ResponseEntity<String> editArchiveWords(@RequestBody ArchiveWordsEntity word) {
        ArchiveWordsEntity updatedWord = archiveWordsService.editArchiveWords(word);
        return ResponseEntity.ok("Word Updated!");
    }

    // Delete
    @PutMapping("/remove/{archiveWordsID}")
    public ResponseEntity<String> removeArchiveWords(@PathVariable int archiveWordsID) {
        archiveWordsService.removeArchiveWords(archiveWordsID);
        return ResponseEntity.ok("Word Removed!");
    }
}
