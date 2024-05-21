package com.typecasters.apitowerofwords.Service;

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
    ArchiveWordsRepository archiveWordsRepository;
    @Autowired
    UserDetailsService userDetailsService;

    public ArchiveWordsEntity insertArchiveWords(int userID, String word) {
        boolean wordExists = false;

        try {
            List<ArchiveWordsEntity> insert = archiveWordsRepository.findAllByUserID(userID);

            for (ArchiveWordsEntity i : insert) {
                if (i.getWord().equals(word)) {
                    wordExists = true;
                    break;
                }
            }

        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("User " + userID + " does not exist");
        }

        if (!wordExists) {
            ArchiveWordsEntity archive = new ArchiveWordsEntity();
            archive.setUserID(userID);
            archive.setWord(word);
            userDetailsService.incrementUserDetailWords(userID);
            return archiveWordsRepository.save(archive);
        } else {
            throw new IllegalArgumentException("Word Already Exist!");
        }
    }

    public List<ArchiveWordsEntity> viewAllArchiveWords(int userID) {
        return archiveWordsRepository.findAllByUserID(userID);
    }

    public Optional<ArchiveWordsEntity> viewArchiveWordsByID(int archiveWordsID) {
        return archiveWordsRepository.findById(archiveWordsID);
    }

    public ArchiveWordsEntity editArchiveWords(ArchiveWordsEntity word) {
        ArchiveWordsEntity edit = new ArchiveWordsEntity();

        try {
            edit = archiveWordsRepository.findById(word.getArchiveWordsID()).get();

            edit.setUserID(word.getUserID());
            edit.setWord(word.getWord());
            edit.setCheck(word.isCheck());

        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("Word " + word.getArchiveWordsID() + " does not exist");
        } finally {
            return archiveWordsRepository.save(edit);
        }
    }

    public ArchiveWordsEntity removeArchiveWords(int archiveWordsID) {
        ArchiveWordsEntity delete = new ArchiveWordsEntity();

        try {
            delete = archiveWordsRepository.findById(archiveWordsID).get();

            delete.setDeleted(true);
        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("Word " + archiveWordsID + " does not exist!");
        } finally {
            return archiveWordsRepository.save(delete);
        }
    }
}
