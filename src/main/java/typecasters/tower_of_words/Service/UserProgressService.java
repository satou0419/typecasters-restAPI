package typecasters.tower_of_words.Service;

import typecasters.tower_of_words.Entity.UserProgressEntity;
import typecasters.tower_of_words.Repository.UserProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserProgressService {

    @Autowired
    UserProgressRepository userProgressRepository;

    //Update tower progress
    public String updateTowerProgress(UserProgressEntity towerProgUpdate, int userProgressID){
        String message = "User Progress Updated!";

        try{
            UserProgressEntity towerProgress = userProgressRepository.findById(userProgressID).get();

            towerProgress.setTowerSectionProgress(towerProgUpdate.getTowerSectionProgress());
            towerProgress.setFloorID(towerProgUpdate.getFloorID());

            userProgressRepository.save(towerProgress);
        }catch(NoSuchElementException e){
            message = "User Progress Id Does not Exist";
        }
        return message;
    }
}
