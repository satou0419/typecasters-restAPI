package typecasters.tower_of_words.Service;

import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Lazy;
import typecasters.tower_of_words.Entity.AchievementEntity;
import typecasters.tower_of_words.Entity.ArchiveAchievementEntity;
import typecasters.tower_of_words.Entity.UserDetailsEntity;
import typecasters.tower_of_words.Entity.UserEntity;
import typecasters.tower_of_words.Repository.ArchiveAchievementRepository;
import typecasters.tower_of_words.Repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ArchiveAchievementService {
    @Autowired
    ArchiveAchievementRepository archiveAchievementRepository;

    @Lazy
    @Autowired
    UserDetailsService userDetailsService;

    @Lazy
    @Autowired
    UserService userService;

    @Lazy
    @Autowired
    AchievementService achievementService;

    public ArchiveAchievementEntity insertArchiveAchievement(ArchiveAchievementEntity archiveAchievement){
        return archiveAchievementRepository.save(archiveAchievement);
    }

    public List<ArchiveAchievementEntity> getAllArchiveAchievement(){
        return archiveAchievementRepository.findAll();
    }

    public Optional<ArchiveAchievementEntity> getArchiveAchievementByID(int archiveAchievementID){
        return archiveAchievementRepository.findById(archiveAchievementID);
    }

    public List<ArchiveAchievementEntity> getArchiveAchievementsByUserID(int userID){
        return archiveAchievementRepository.findByUserID(userID);
    }

    public Optional<ArchiveAchievementEntity> getArchiveAchievementByUserIDAndAchievementID(int userID, int achievementID){
        AchievementEntity achievementEntity = new AchievementEntity();
        achievementEntity.setAchievementID(achievementID);
        return archiveAchievementRepository.findOneByUserIDAndAchievementID(userID, achievementEntity);
    }

    public ArchiveAchievementEntity updateArchiveAchievement(ArchiveAchievementEntity newArchiveAchievementDetails){
        ArchiveAchievementEntity updatedArchiveAchievement = getArchiveAchievementByID(newArchiveAchievementDetails.getArchiveAchievementID())
                .orElseThrow(() -> new NoSuchElementException("Archive Achievement doesn't exist!"));

        updatedArchiveAchievement.setUnlockedDate(newArchiveAchievementDetails.getUnlockedDate());
        updatedArchiveAchievement.setUnlocked(newArchiveAchievementDetails.isUnlocked());

        return archiveAchievementRepository.save(updatedArchiveAchievement);
    }

    @Transactional
    public void prepopulateNewAchievementForExistingUsers(AchievementEntity newAchievement) {

        List<UserEntity> allUsers = userService.getAllUsers();

        for (UserEntity user : allUsers) {
            int userID = user.getUserID();

            Optional<ArchiveAchievementEntity> existingAchievement =
                    getArchiveAchievementByUserIDAndAchievementID(userID, newAchievement.getAchievementID());

            if (existingAchievement.isEmpty()) {

                ArchiveAchievementEntity archiveAchievement = new ArchiveAchievementEntity();
                archiveAchievement.setUserID(userID);
                archiveAchievement.setAchievementID(newAchievement);
                archiveAchievement.setUnlocked(false);
                archiveAchievement.setUnlockedDate(null);

                insertArchiveAchievement(archiveAchievement);
            }
        }
    }

    @Transactional
    public List<AchievementEntity> checkUserEligibilityForAchievements(int userID, String achievementType) {

        UserDetailsEntity userDetails = userDetailsService.getUserDetails(userID);

        List<AchievementEntity> achievements = achievementService.getAchievementsByType(achievementType);

        List<AchievementEntity> unlockedAchievements = new ArrayList<>();

        for (AchievementEntity achievement : achievements) {

            int criteria = achievement.getCriteria();

            boolean isEligible = false;
            switch (achievementType.toLowerCase()) {
                case "words":
                    isEligible = userDetails.getWordsCollected() >= criteria;
                    break;
                case "floors":
                    isEligible = userDetails.getFloorCount() >= criteria;
                    break;
                case "achievements":
                    isEligible = userDetails.getAchievementCount() >= criteria;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid achievement type: " + achievementType);
            }

            if (isEligible) {
                Optional<ArchiveAchievementEntity> existingAchievement =
                        getArchiveAchievementByUserIDAndAchievementID(userID, achievement.getAchievementID());

                if (existingAchievement.isPresent()) {
                    ArchiveAchievementEntity archiveAchievement = existingAchievement.get();

                    if (!archiveAchievement.isUnlocked()) {

                        archiveAchievement.setUnlocked(true);
                        archiveAchievement.setUnlockedDate(new Date());

                        updateArchiveAchievement(archiveAchievement);

                        userDetailsService.incrementUserAchievementCount(userID);

                        achievementService.incrementTotalUnlocked(achievement.getAchievementID());

                        unlockedAchievements.add(achievement);
                    }
                }
            }
        }

        return unlockedAchievements;
    }

    @Transactional
    public ArchiveAchievementEntity forNotificationsArchiveAchievement(int userID, int achievementID){
        ArchiveAchievementEntity updatedArchiveAchievement = getArchiveAchievementByUserIDAndAchievementID(userID, achievementID)
                .orElseThrow(() -> new NoSuchElementException("Archive Achievement doesn't exist!"));

        updatedArchiveAchievement.setChecked(true);

        return archiveAchievementRepository.save(updatedArchiveAchievement);
    }
}
