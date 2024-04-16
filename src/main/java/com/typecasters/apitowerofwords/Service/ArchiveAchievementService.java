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

    public Optional<ArchiveAchievementEntity> viewArchiveAchievementByID(int archive_achievement_id) {
        return AAR.findById(archive_achievement_id);
    }

    public ArchiveAchievementEntity editArchiveAchievement(int archive_achievement_id, ArchiveAchievementEntity new_archive_achievement) {
        ArchiveAchievementEntity edit = new ArchiveAchievementEntity();

        try {
            edit = AAR.findById(archive_achievement_id).get();

            edit.setArchive_achievement_name(new_archive_achievement.getArchive_achievement_name());
            edit.setArchive_achievement_description(new_archive_achievement.getArchive_achievement_description());
            edit.setArchive_achievement_image_path(new_archive_achievement.getArchive_achievement_image_path());

        }catch(NoSuchElementException ex) {
            throw new NoSuchElementException ("Achievement " + archive_achievement_id + " does not exist");
        }finally {
            return AAR.save(edit);
        }
    }

    public ArchiveAchievementEntity removeArchiveAchievement(int archive_achievement_id) {
        ArchiveAchievementEntity delete = new ArchiveAchievementEntity();

        try {
            delete = AAR.findById(archive_achievement_id).get();

            delete.setArchive_achievement_is_deleted(true);
        }catch(NoSuchElementException ex) {
            throw new NoSuchElementException("Achievement " + archive_achievement_id + " does not exist!");
        }finally {
            return AAR.save(delete);
        }
    }
}
