package com.typecasters.apitowerofwords.Service;

import com.typecasters.apitowerofwords.Entity.ArchiveAchievementEntity;
import com.typecasters.apitowerofwords.Entity.ArchiveWordsEntity;
import com.typecasters.apitowerofwords.Repository.ArchiveWordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ArchiveWordsService {
    @Autowired
    ArchiveWordsRepository AWR;

    public ArchiveWordsEntity insertArchiveWords(ArchiveWordsEntity word) {
        return AWR.save(word);
    }

    public List<ArchiveWordsEntity> viewAllArchiveWords() {
        return AWR.findAll();
    }

    public Optional<ArchiveWordsEntity> viewArchiveWordsByID(int archive_words_id) {
        return AWR.findById(archive_words_id);
    }

    public ArchiveWordsEntity editArchiveWords(ArchiveWordsEntity word) {
        ArchiveWordsEntity edit = new ArchiveWordsEntity();

        try {
            edit = AWR.findById(word.getAWID()).get();

            edit.setUID(word.getUID());
            edit.setWord(word.getWord());
            edit.setCheck(word.isCheck());

        }catch(NoSuchElementException ex) {
            throw new NoSuchElementException ("Word " + word.getAWID() + " does not exist");
        }finally {
            return AWR.save(edit);
        }
    }

    public ArchiveWordsEntity removeArchiveWords(int AWID) {
        ArchiveWordsEntity delete = new ArchiveWordsEntity();

        try {
            delete = AWR.findById(AWID).get();

            delete.setDeleted(true);
        }catch(NoSuchElementException ex) {
            throw new NoSuchElementException("Word " + AWID + " does not exist!");
        }finally {
            return AWR.save(delete);
        }
    }
}
