package typecasters.tower_of_words.Service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import typecasters.tower_of_words.Entity.CharacterEntity;
import typecasters.tower_of_words.Entity.UserCharacterEntity;
import typecasters.tower_of_words.Entity.UserDetailsEntity;
import typecasters.tower_of_words.Exception.CharacterNotFoundException;
import typecasters.tower_of_words.Exception.InsufficientCreditException;
import typecasters.tower_of_words.Exception.UserCharacterAlreadyExistException;
import typecasters.tower_of_words.Exception.UserCharacterNotFoundException;
import typecasters.tower_of_words.Repository.UserCharacterRepository;
import typecasters.tower_of_words.Repository.UserItemRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserCharacterService {
    @Autowired
    UserCharacterRepository userCharacterRepository;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    CharacterService characterService;

    public UserCharacterEntity insertUserCharacter(UserCharacterEntity user){
        return userCharacterRepository.save(user);
    }

    public List<UserCharacterEntity> getAllUserCharacter() {
        return userCharacterRepository.findAll();
    }

    public Optional<UserCharacterEntity> getUserCharacterById(int userCharacterID){
        return userCharacterRepository.findById(userCharacterID);
    }

    public List<UserCharacterEntity> getAllUserCharacterByUserID(int userID){
        return userCharacterRepository.findAllByUserID(userID);
    }

    @SuppressWarnings({"finally", "ReturnInsideFinallyBlock"})
    @Transactional
    public UserCharacterEntity updateUserCharacter (int userCharacterId, UserCharacterEntity newUserItemDetails){

        UserCharacterEntity userCharacter = new UserCharacterEntity();

        try{

            userCharacter = userCharacterRepository.findById(userCharacterId).get();
            userCharacter.setOwned(newUserItemDetails.isOwned());
        }catch (NoSuchElementException ex){
            throw new NoSuchElementException("User Character " + userCharacterId + " does not exist!");

        }finally {
            return userCharacterRepository.save(userCharacter);
        }
    }

    public String deleteUserCharacter(int userCharacterID){
        String msg = "";

        if(userCharacterRepository.findById(userCharacterID).isPresent()){
            userCharacterRepository.deleteById(userCharacterID);
            msg = "User Character " + userCharacterID + " is successfully deleted!";
        }else{
            msg = "User Character " + userCharacterID + " does not exist!";
        }

        return msg;
    }

    public Optional<Integer> getUserCharacterIDByUserIDAndCharacterID(int userID, int characterID){
        CharacterEntity characterEntity = new CharacterEntity();
        characterEntity.setCharacterID(characterID);

        return userCharacterRepository.findUserCharacterIDByUserIDAndCharacterID(userID, characterEntity);
    }

    public Optional<UserCharacterEntity> getUserCharacterByUserIDAndCharacterID(int userID, int characterID){
        CharacterEntity characterEntity = new CharacterEntity();
        characterEntity.setCharacterID(characterID);

        return userCharacterRepository.findOneByUserIDAndCharacterID(userID, characterEntity);
    }

    @Transactional
    public String buyCharacter(int userID, int characterID){
        try{
            UserDetailsEntity userDetails = userDetailsService.getUserDetails(userID);
            Optional<CharacterEntity> op_char = characterService.getCharacterByID(characterID);

            if(op_char.isPresent()){

                CharacterEntity character = op_char.get();
                int characterPrice = character.getPrice();

                if(userDetails.getCreditAmount() < characterPrice){
                    throw new InsufficientCreditException("Insufficient credit to buy this character.");
                }

                userDetailsService.updateUserCredit(userDetails.getUserDetailsID(), -characterPrice);
                Optional<UserCharacterEntity> isExistUserCharacterObject = getUserCharacterByUserIDAndCharacterID(userID, characterID);

                if(isExistUserCharacterObject.isPresent()){
                    UserCharacterEntity existingUserCharacterObject = isExistUserCharacterObject.get();

                    if(existingUserCharacterObject.isOwned()){
                        throw new UserCharacterAlreadyExistException("You already own this character!");
                    } else {
                        existingUserCharacterObject.setOwned(true);
                        userCharacterRepository.save(existingUserCharacterObject);
                    }

                } else {
                    UserCharacterEntity newUserCharacter = new UserCharacterEntity(userID, true, character);
                    insertUserCharacter(newUserCharacter);
                }

                return "Character bought successfully!";

            } else {
                return "Character not found.";
            }
        }catch (InsufficientCreditException | UserCharacterAlreadyExistException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("An error occurred while processing the request.", e);
        }

    }

    @Transactional
    public String equipCharacter(int userID, int characterID){
        try{
            UserDetailsEntity userDetails = userDetailsService.getUserDetails(userID);
            Optional<UserCharacterEntity> isExistUserCharacters = getUserCharacterByUserIDAndCharacterID(userID, characterID);
            Optional<CharacterEntity> op_char = characterService.getCharacterByID(characterID);

            if(op_char.isPresent()){
                if(isExistUserCharacters.isPresent()){
                    UserCharacterEntity existingUserCharacterObject = isExistUserCharacters.get();
                    CharacterEntity character = op_char.get();

                    int userDetailsID = userDetails.getUserDetailsID();

                    if(existingUserCharacterObject.isOwned()){
                        if(userDetails.getEquipped_character().equals(character.getImagePath())){
                            throw new UserCharacterAlreadyExistException("You already equipped this character!");
                        }else{
                            userDetailsService.updateEquippedCharacter(userDetailsID, character.getImagePath());
                        }
                    }else{
                        throw new IllegalArgumentException("You do not own this character!");
                    }

                }else{
                    throw new UserCharacterNotFoundException("User Character does not Exist!");
                }

                return "User Character " + characterID + " equipped successfully to " + userID + ".";
            }else{
                throw new CharacterNotFoundException("Character does not exist!");
            }
        } catch(UserCharacterNotFoundException | IllegalArgumentException | UserCharacterAlreadyExistException | CharacterNotFoundException e){
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("An error occurred while processing the request.", e);
        }
    }

}
