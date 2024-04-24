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

    public ArchiveWordsEntity editArchiveWords(int archive_words_id, ArchiveWordsEntity new_archive_words) {
        ArchiveWordsEntity edit = new ArchiveWordsEntity();

        try {
            edit = AWR.findById(archive_words_id).get();

            edit.setArchive_words_user_id(new_archive_words.getArchive_words_user_id());
            edit.setArchive_words_word(new_archive_words.getArchive_words_word());
            edit.setArchive_words_check(new_archive_words.isArchive_words_check());

        }catch(NoSuchElementException ex) {
            throw new NoSuchElementException ("Word " + archive_words_id + " does not exist");
        }finally {
            return AWR.save(edit);
        }
    }

    public ArchiveWordsEntity removeArchiveWords(int archive_words_id) {
        ArchiveWordsEntity delete = new ArchiveWordsEntity();

        try {
            delete = AWR.findById(archive_words_id).get();

            delete.setArchive_words_is_deleted(true);
        }catch(NoSuchElementException ex) {
            throw new NoSuchElementException("Word " + archive_words_id + " does not exist!");
        }finally {
            return AWR.save(delete);
        }
    }
}
