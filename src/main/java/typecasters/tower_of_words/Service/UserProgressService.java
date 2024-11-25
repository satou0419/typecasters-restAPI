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

    public String updateTowerProgress(UserProgressEntity towerProgUpdate, int userProgressID) {
        String message = "User Progress Updated!";

        try {
            UserProgressEntity towerProgress = userProgressRepository.findById(userProgressID).get();

            // This newly added conditions is to stop it from updating even when going to previous floors.
            // Floors
            if (towerProgUpdate.getSpellingFloorID() > towerProgress.getSpellingFloorID()) {
                towerProgress.setSpellingFloorID(towerProgUpdate.getSpellingFloorID());
            }

            if (towerProgUpdate.getSyllableFloorID() > towerProgress.getSyllableFloorID()) {
                towerProgress.setSyllableFloorID(towerProgUpdate.getSyllableFloorID());
            }

            if (towerProgUpdate.getSilentFloorID() > towerProgress.getSilentFloorID()) {
                towerProgress.setSilentFloorID(towerProgUpdate.getSilentFloorID());
            }

            // Sections
            if(towerProgUpdate.getSpellingSectionProgress() > towerProgress.getSpellingSectionProgress()){
                towerProgress.setSpellingSectionProgress(towerProgUpdate.getSpellingSectionProgress());
            }

            if(towerProgUpdate.getSyllableSectionProgress() > towerProgress.getSyllableSectionProgress()){
                towerProgress.setSyllableSectionProgress(towerProgUpdate.getSyllableSectionProgress());
            }

            if(towerProgUpdate.getSilentSectionProgress() > towerProgress.getSilentSectionProgress()){
                towerProgress.setSilentSectionProgress(towerProgUpdate.getSilentSectionProgress());
            }

            // Save updated entity to the repository
            userProgressRepository.save(towerProgress);
        } catch (NoSuchElementException e) {
            message = "User Progress ID Does Not Exist";
        }

        return message;
    }

//    //Update tower progress
//    public String updateTowerProgress(UserProgressEntity towerProgUpdate, int userProgressID){
//        String message = "User Progress Updated!";
//
//        try{
//            UserProgressEntity towerProgress = userProgressRepository.findById(userProgressID).get();
//
//            towerProgress.setSpellingSectionProgress(towerProgUpdate.getSpellingSectionProgress());
//            towerProgress.setSilentSectionProgress(towerProgUpdate.getSilentSectionProgress());
//            towerProgress.setSyllableSectionProgress(towerProgUpdate.getSyllableSectionProgress());
//
//            towerProgress.setSpellingFloorID(towerProgUpdate.getSpellingFloorID());
//            towerProgress.setSilentFloorID(towerProgUpdate.getSilentFloorID());
//            towerProgress.setSyllableFloorID(towerProgUpdate.getSyllableFloorID());
//
//            userProgressRepository.save(towerProgress);
//        }catch(NoSuchElementException e){
//            message = "User Progress Id Does not Exist";
//        }
//        return message;
//    }


}
