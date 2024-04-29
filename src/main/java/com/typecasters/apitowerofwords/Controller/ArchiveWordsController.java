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

    @GetMapping("/view_by_id/{AWID}")
    public Optional<ArchiveWordsEntity> viewArchiveWordsByID(@PathVariable int AWID) {
        return AWS.viewArchiveWordsByID(AWID);
    }

    //Update
    @PutMapping("/edit")
    public ArchiveWordsEntity editArchiveWords(@RequestBody ArchiveWordsEntity word) {
        return AWS.editArchiveWords(word);
    }

    //Delete
    @PutMapping("/remove/{AWID}")
    public ArchiveWordsEntity removeArchiveWords(@PathVariable int AWID) {
        return AWS.removeArchiveWords(AWID);
    }
}
