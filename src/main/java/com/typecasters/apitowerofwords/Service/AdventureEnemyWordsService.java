package com.typecasters.apitowerofwords.Service;

import com.typecasters.apitowerofwords.Entity.AdventureEnemyWordsEntity;
import com.typecasters.apitowerofwords.Repository.AdventureEnemyWordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AdventureEnemyWordsService {
    @Autowired
    AdventureEnemyWordsRepository AEWR;

    public AdventureEnemyWordsEntity insertAdventureEnemyWords(AdventureEnemyWordsEntity enemy) {
        return AEWR.save(enemy);
    }

    public List<AdventureEnemyWordsEntity> viewAllAdventureEnemyWords() {
        return AEWR.findAll();
    }

    public Optional<AdventureEnemyWordsEntity> viewAdventureEnemyWordsByID(int enemyID) {
        return AEWR.findById(enemyID);
    }

    public AdventureEnemyWordsEntity editAdventureEnemyWords(AdventureEnemyWordsEntity word) {
        AdventureEnemyWordsEntity edit = new AdventureEnemyWordsEntity();

        try {
            edit = AEWR.findById(word.getAEWID()).get();

            edit.setWord(word.getWord());

        }catch(NoSuchElementException ex) {
            throw new NoSuchElementException ("Achievement " + word.getAEWID() + " does not exist");
        }finally {
            return AEWR.save(edit);
        }
    }

    public AdventureEnemyWordsEntity removeAdventureEnemyWords(int wordID) {
        AdventureEnemyWordsEntity delete = new AdventureEnemyWordsEntity();

        try {
            delete = AEWR.findById(wordID).get();

            delete.setDeleted(true);
        }catch(NoSuchElementException ex) {
            throw new NoSuchElementException("Achievement " + wordID + " does not exist!");
        }finally {
            return AEWR.save(delete);
        }
    }
}
