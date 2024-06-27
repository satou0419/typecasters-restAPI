package typecasters.tower_of_words.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import typecasters.tower_of_words.Entity.UserArchiveAchievementEntity;
import typecasters.tower_of_words.Repository.UserArchiveAchievementRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserArchiveAchievementService {
    @Autowired
    UserArchiveAchievementRepository userArchiveAchievementRepository;

    public UserArchiveAchievementEntity insertUserArchiveAchievement(UserArchiveAchievementEntity user){
        return userArchiveAchievementRepository.save(user);
    }

    public List<UserArchiveAchievementEntity> viewAllUserArchiveAchievement() {
        return userArchiveAchievementRepository.findAll();
    }

    public UserArchiveAchievementEntity editUserArchiveAchievement(UserArchiveAchievementEntity user){
        UserArchiveAchievementEntity edit = new UserArchiveAchievementEntity();

        try {
            edit = userArchiveAchievementRepository.findById(user.getUserArchiveAchievementID()).get();

            edit.setUserID(user.getUserID());
            edit.setArchiveAchievementID(user.getArchiveAchievementID());

        }catch(NoSuchElementException ex) {
            throw new NoSuchElementException ("Achievement " + user.getUserArchiveAchievementID() + " does not exist");
        }finally {
            return userArchiveAchievementRepository.save(edit);
        }
    }

    public void deleteUserArchiveAchievement(int id) {
        userArchiveAchievementRepository.deleteById(id);
    }
}
