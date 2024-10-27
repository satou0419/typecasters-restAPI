package typecasters.tower_of_words.Service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import typecasters.tower_of_words.Entity.UserDetailsEntity;
import typecasters.tower_of_words.Entity.UserGameTutorialStatusEntity;
import typecasters.tower_of_words.Repository.UserDetailsRepository;
import typecasters.tower_of_words.Repository.UserGameTutorialStatusRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserGameTutorialStatusService {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private UserGameTutorialStatusRepository userGameTutorialStatusRepository;

    public UserGameTutorialStatusEntity insertUserGameTutorialStatus(UserGameTutorialStatusEntity userGameTutorialStatus){
        userDetailsRepository.findById(userGameTutorialStatus.getUserDetailsID())
                .orElseThrow(() -> new NoSuchElementException("User doesn't exist with this User Details ID = " + userGameTutorialStatus.getUserDetailsID()));

        return userGameTutorialStatusRepository.save(userGameTutorialStatus);
    }

    public List<UserGameTutorialStatusEntity> getAllUserGameTutorialStatus(){
        return userGameTutorialStatusRepository.findAll();
    }

    public Optional<UserGameTutorialStatusEntity> getUserGameTutorialStatusByID(int userGameTutorialStatusID){
        return userGameTutorialStatusRepository.findById(userGameTutorialStatusID);
    }

    public Optional<UserGameTutorialStatusEntity> getUserGameTutorialStatusByUserDetailsID(int userDetailsID){
        userDetailsRepository.findById(userDetailsID)
                .orElseThrow(() -> new NoSuchElementException("User doesn't exist with this User Details ID = " + userDetailsID));

        return userGameTutorialStatusRepository.findOneByUserDetailsID(userDetailsID);
    }

    public UserGameTutorialStatusEntity updateUserGameTutorialStatusByUserDetailsID(
            UserGameTutorialStatusEntity userGameTutorialStatus)
    {
        UserGameTutorialStatusEntity existingUserGameTutorialStatusObject = getUserGameTutorialStatusByUserDetailsID(userGameTutorialStatus.getUserDetailsID())
                .orElseThrow(() -> new NoSuchElementException("User Game Tutorial ID = " + userGameTutorialStatus.getUserGameTutorialStatusID() + " with this User Details ID = " + userGameTutorialStatus.getUserDetailsID()));

        existingUserGameTutorialStatusObject.setFirstGamePlayground(userGameTutorialStatus.isFirstGamePlayground());
        existingUserGameTutorialStatusObject.setFirstGameSilentSimu(userGameTutorialStatus.isFirstGameSilentSimu());
        existingUserGameTutorialStatusObject.setFirstGameSilentAdventure(userGameTutorialStatus.isFirstGameSilentAdventure());
        existingUserGameTutorialStatusObject.setFirstGameSpellingAdventure(userGameTutorialStatus.isFirstGameSpellingAdventure());
        existingUserGameTutorialStatusObject.setFirstGameSyllableSimu(userGameTutorialStatus.isFirstGameSyllableSimu());
        existingUserGameTutorialStatusObject.setFirstGameSpellingAdventure(userGameTutorialStatus.isFirstGameSpellingAdventure());
        existingUserGameTutorialStatusObject.setFirstGameSyllableAdventure(userGameTutorialStatus.isFirstGameSyllableAdventure());

        return userGameTutorialStatusRepository.save(existingUserGameTutorialStatusObject);
    }

    // Different function, as it just turns the specific column to True depending on tutorialType (String), returns
    // a confirmation string if ever it turns to true, and also if it's already true
    // Uses a switch() function and also toLowerCase() function, idea copied from ArchiveAchievement
    @Transactional
    public String updateTutorialGameplayStatusByUserDetailsID(
            int userDetailsID,
            String tutorialType)
    {

        UserGameTutorialStatusEntity existingUserGameTutorialStatusObject = getUserGameTutorialStatusByUserDetailsID(userDetailsID)
                .orElseThrow(() -> new NoSuchElementException("User Game Tutorial Status doesn't exist with this User Details ID = " + userDetailsID));

        switch (tutorialType.toLowerCase()) {
            case "syllableadventure":
                if (existingUserGameTutorialStatusObject.isFirstGameSyllableAdventure()) {
                    return "The user is already done with the tutorial. The user can recheck it in another menu.";
                }
                existingUserGameTutorialStatusObject.setFirstGameSyllableAdventure(true);
                break;

            case "silentadventure":
                if (existingUserGameTutorialStatusObject.isFirstGameSilentAdventure()) {
                    return "The user is already done with the tutorial. The user can recheck it in another menu.";
                }
                existingUserGameTutorialStatusObject.setFirstGameSilentAdventure(true);
                break;

            case "spellingadventure":
                if (existingUserGameTutorialStatusObject.isFirstGameSpellingAdventure()) {
                    return "The user is already done with the tutorial. The user can recheck it in another menu.";
                }
                existingUserGameTutorialStatusObject.setFirstGameSpellingAdventure(true);
                break;

            case "playground":
                if (existingUserGameTutorialStatusObject.isFirstGamePlayground()) {
                    return "The user is already done with the tutorial. The user can recheck it in another menu.";
                }
                existingUserGameTutorialStatusObject.setFirstGamePlayground(true);
                break;

            case "syllablesimu":
                if (existingUserGameTutorialStatusObject.isFirstGameSyllableSimu()) {
                    return "The user is already done with the tutorial. The user can recheck it in another menu.";
                }
                existingUserGameTutorialStatusObject.setFirstGameSyllableSimu(true);
                break;

            case "silentsimu":
                if (existingUserGameTutorialStatusObject.isFirstGameSilentSimu()) {
                    return "The user is already done with the tutorial. The user can recheck it in another menu.";
                }
                existingUserGameTutorialStatusObject.setFirstGameSilentSimu(true);
                break;

            case "spellingsimu":
                if (existingUserGameTutorialStatusObject.isFirstGameSpellingSimu()) {
                    return "The user is already done with the tutorial. The user can recheck it in another menu.";
                }
                existingUserGameTutorialStatusObject.setFirstGameSpellingSimu(true);
                break;

            default:
                return "Invalid tutorial type provided.";
        }

        userGameTutorialStatusRepository.save(existingUserGameTutorialStatusObject);

        return "The " + tutorialType + " tutorial status has been successfully set to true.";
    }
}
