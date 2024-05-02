package com.typecasters.apitowerofwords.Controller;

import org.springframework.beans.factory.annotation.Autowired;
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

    //Create
    @PostMapping("/insert")
    public ArchiveWordsEntity insertArchiveWords(@RequestBody ArchiveWordsEntity word) {
        return archiveWordsService.insertArchiveWords(word.getUserID(), word);
    }

    //Read
    @GetMapping("/view/{userID}")
    public List<ArchiveWordsEntity> viewAllArchiveWords(int userID) {
        return archiveWordsService.viewAllArchiveWords(userID);
    }

    @GetMapping("/view_by_id/{archiveWordsID}")
    public Optional<ArchiveWordsEntity> viewArchiveWordsByID(@PathVariable int archiveWordsID) {
        return archiveWordsService.viewArchiveWordsByID(archiveWordsID);
    }

    //Update
    @PutMapping("/edit")
    public ArchiveWordsEntity editArchiveWords(@RequestBody ArchiveWordsEntity word) {
        return archiveWordsService.editArchiveWords(word);
    }

    //Delete
    @PutMapping("/remove/{archiveWordsID}")
    public ArchiveWordsEntity removeArchiveWords(@PathVariable int archiveWordsID) {
        return archiveWordsService.removeArchiveWords(archiveWordsID);
    }
}
