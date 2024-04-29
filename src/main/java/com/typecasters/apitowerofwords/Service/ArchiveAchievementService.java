package com.typecasters.apitowerofwords.Service;

import com.typecasters.apitowerofwords.Entity.ArchiveAchievementEntity;
import com.typecasters.apitowerofwords.Repository.ArchiveAchievementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ArchiveAchievementService {
    @Autowired
    ArchiveAchievementRepository AAR;

    public ArchiveAchievementEntity insertArchiveAchievement(ArchiveAchievementEntity achievement) {
        return AAR.save(achievement);
    }

    public List<ArchiveAchievementEntity> viewAllArchiveAchievement() {
        return AAR.findAll();
    }

    public Optional<ArchiveAchievementEntity> viewArchiveAchievementByID(int AAID) {
        return AAR.findById(AAID);
    }

    public ArchiveAchievementEntity editArchiveAchievement(ArchiveAchievementEntity achievement) {
        ArchiveAchievementEntity edit = new ArchiveAchievementEntity();

        try {
            edit = AAR.findById(achievement.getAAID()).get();

            edit.setName(achievement.getName());
            edit.setDescription(achievement.getDescription());
            edit.setImagePath(achievement.getImagePath());

        }catch(NoSuchElementException ex) {
            throw new NoSuchElementException ("Achievement " + achievement.getAAID() + " does not exist");
        }finally {
            return AAR.save(edit);
        }
    }

    public ArchiveAchievementEntity removeArchiveAchievement(int AAID) {
        ArchiveAchievementEntity delete = new ArchiveAchievementEntity();

        try {
            delete = AAR.findById(AAID).get();

            delete.setDeleted(true);
        }catch(NoSuchElementException ex) {
            throw new NoSuchElementException("Achievement " + AAID + " does not exist!");
        }finally {
            return AAR.save(delete);
        }
    }
}
