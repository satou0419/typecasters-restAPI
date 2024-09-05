package typecasters.tower_of_words.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import typecasters.tower_of_words.Entity.AchievementEntity;
import typecasters.tower_of_words.Entity.UserDetailsEntity;
import typecasters.tower_of_words.Repository.AchievementRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AchievementService {

    @Autowired
    private AchievementRepository achievementRepository;

    @Autowired
    @Lazy
    private ArchiveAchievementService archiveAchievementService;

    public AchievementEntity insertAchievement(AchievementEntity achievement) {
        AchievementEntity savedAchievement = achievementRepository.save(achievement);

        archiveAchievementService.prepopulateNewAchievementForExistingUsers(savedAchievement);

        return savedAchievement;
    }

    public List<AchievementEntity> getAchievementsByType(String achievementType){
        return achievementRepository.findByAchievementType(achievementType);
    }

    public List<AchievementEntity> getAllAchievements() {
        return achievementRepository.findAll();
    }

    public Optional<AchievementEntity> getOneAchievementByAchievementID(
            int achievementID)
    {
        return achievementRepository.findById(achievementID);
    }

    public String incrementTotalUnlocked(int achievementID){
        AchievementEntity achievement = getOneAchievementByAchievementID(achievementID)
                .orElseThrow(() -> new NoSuchElementException("Achievement ID doesn't exist!"));

        achievement.setTotalUnlocked(achievement.getTotalUnlocked()+1);
        achievementRepository.save(achievement);

        return "Total Unlocked incremented!";
    }

    public AchievementEntity updateAchievement(
            int achievementID,
            AchievementEntity newAchievementDetails)
    {
        AchievementEntity existingAchievementEntity = getOneAchievementByAchievementID(achievementID)
                .orElseThrow(() -> new NoSuchElementException("Achievement ID# " + achievementID + " doesn't exist!"));

        existingAchievementEntity.setName(newAchievementDetails.getName());
        existingAchievementEntity.setDescription(newAchievementDetails.getDescription());
        existingAchievementEntity.setImagePath(newAchievementDetails.getImagePath());
        existingAchievementEntity.setAchievementType(newAchievementDetails.getAchievementType());
        existingAchievementEntity.setCriteria(newAchievementDetails.getCriteria());

        return achievementRepository.save(existingAchievementEntity);
    }

    public void deleteAchievement(int achievementID){
        AchievementEntity checkAchievementEntity = getOneAchievementByAchievementID(achievementID)
                .orElseThrow(() -> new NoSuchElementException("Achievement ID# " + achievementID + " doesn't exist!"));

        achievementRepository.deleteById(checkAchievementEntity.getAchievementID());
    }
}
