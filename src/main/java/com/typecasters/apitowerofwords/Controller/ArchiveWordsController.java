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
    ArchiveWordsService AWS;

    //Create
    @PostMapping("/insert")
    public ArchiveWordsEntity insertArchiveWords(@RequestBody ArchiveWordsEntity word) {
        return AWS.insertArchiveWords(word);
    }

    //Read
    @GetMapping("/view")
    public List<ArchiveWordsEntity> viewAllArchiveWords() {
        return AWS.viewAllArchiveWords();
    }

    @GetMapping("/view_by_id/{archive_words_id}")
    public Optional<ArchiveWordsEntity> viewArchiveWordsByID(@PathVariable int archive_words_id) {
        return AWS.viewArchiveWordsByID(archive_words_id);
    }

    //Update
    @PutMapping("/edit/{archive_words_id}")
    public ArchiveWordsEntity editArchiveWords(@PathVariable int archive_words_id,@RequestBody ArchiveWordsEntity new_archive_words) {
        return AWS.editArchiveWords(archive_words_id, new_archive_words);
    }

    //Delete
    @PutMapping("/remove/{archive_words_id}")
    public ArchiveWordsEntity removeArchiveWords(@PathVariable int archive_words_id) {
        return AWS.removeArchiveWords(archive_words_id);
    }
}
