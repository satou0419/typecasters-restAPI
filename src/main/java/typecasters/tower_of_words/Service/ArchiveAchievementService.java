package typecasters.tower_of_words.Service;

import typecasters.tower_of_words.Entity.ArchiveAchievementEntity;
import typecasters.tower_of_words.Repository.ArchiveAchievementRepository;
import typecasters.tower_of_words.Repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ArchiveAchievementService {
    @Autowired
    ArchiveAchievementRepository archiveAchievementRepository;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    UserDetailsRepository userDetailsRepository;

    public ArchiveAchievementEntity insertArchiveAchievement(int userID, ArchiveAchievementEntity achievement) {
        boolean achievementExists = false;

        try {
            List<ArchiveAchievementEntity> insert = archiveAchievementRepository.findByUserID(userID);

            for (ArchiveAchievementEntity i : insert) {
                if (i.getName().equals(achievement.getName())) {
                    achievementExists = true;
                    break;
                }
            }

        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("Achievement " + achievement.getArchiveAchievementID() + " does not exist");
        }

        if (!achievementExists) {
            userDetailsService.incrementUserDetailWords(userID);
            return archiveAchievementRepository.save(achievement);
        } else {
            System.out.print("Achievement Already Exist!");
            return achievement;
        }
    }

    public List<ArchiveAchievementEntity> viewAllArchiveAchievement(int userID) {
        return archiveAchievementRepository.findByUserID(userID);
    }

    public Optional<ArchiveAchievementEntity> viewArchiveAchievementByID(int archiveAchievementID) {
        return archiveAchievementRepository.findById(archiveAchievementID);
    }

    public ArchiveAchievementEntity editArchiveAchievement(ArchiveAchievementEntity achievement) {
        ArchiveAchievementEntity edit = new ArchiveAchievementEntity();

        try {
            edit = archiveAchievementRepository.findById(achievement.getArchiveAchievementID()).get();

            edit.setName(achievement.getName());
            edit.setDescription(achievement.getDescription());
            edit.setImagePath(achievement.getImagePath());
            edit.setChecked(achievement.isChecked());

        }catch(NoSuchElementException ex) {
            throw new NoSuchElementException ("Achievement " + achievement.getArchiveAchievementID() + " does not exist");
        }finally {
            return archiveAchievementRepository.save(edit);
        }
    }

    public String removeArchiveAchievement(int archiveAchievementID) {
        String msg = "";

        if(archiveAchievementRepository.findById(archiveAchievementID).isPresent()) {
            archiveAchievementRepository.deleteById(archiveAchievementID);

            msg = "Item " + archiveAchievementID + " is successfully deleted!";
        }

        return msg;
    }
}
