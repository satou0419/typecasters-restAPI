package typecasters.tower_of_words.Controller;

import typecasters.tower_of_words.Entity.AdventureEnemyWordsEntity;
import typecasters.tower_of_words.Service.AdventureEnemyWordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/adventure_enemy_words")
public class AdventureEnemyWordsController {
    @Autowired
    AdventureEnemyWordsService AEWS;

    //Create
    @PostMapping("/insert")
    public AdventureEnemyWordsEntity insertAdventureEnemyWords(@RequestBody AdventureEnemyWordsEntity word) {
        return AEWS.insertAdventureEnemyWords(word);
    }

    //Read
    @GetMapping("/view")
    public List<AdventureEnemyWordsEntity> viewAllAdventureEnemyWords() {
        return AEWS.viewAllAdventureEnemyWords();
    }

    @GetMapping("/view_by_id/{AEWID}")
    public Optional<AdventureEnemyWordsEntity> viewAdventureEnemyWordsByID(@PathVariable int AEWID) {
        return AEWS.viewAdventureEnemyWordsByID(AEWID);
    }

    //Update
    @PutMapping("/edit")
    public AdventureEnemyWordsEntity editAdventureEnemyWords(@RequestBody AdventureEnemyWordsEntity word) {
        return AEWS.editAdventureEnemyWords(word);
    }

    //Delete
    @PutMapping("/remove/{AEWID}")
    public AdventureEnemyWordsEntity removeAdventureEnemyWords(@PathVariable int AEWID) {
        return AEWS.removeAdventureEnemyWords(AEWID);
    }
}
