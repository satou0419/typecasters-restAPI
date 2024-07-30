package typecasters.tower_of_words.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import typecasters.tower_of_words.Entity.UserCharacterEntity;
import typecasters.tower_of_words.Exception.CharacterNotFoundException;
import typecasters.tower_of_words.Exception.InsufficientCreditException;
import typecasters.tower_of_words.Exception.UserCharacterAlreadyExistException;
import typecasters.tower_of_words.Exception.UserCharacterNotFoundException;
import typecasters.tower_of_words.Response.NoDataResponse;
import typecasters.tower_of_words.Response.Response;
import typecasters.tower_of_words.Service.UserCharacterService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user_character")
public class UserCharacterController {

    @Autowired
    private UserCharacterService userCharacterService;

    @PostMapping("/insert_user_character")
    public ResponseEntity<Object> insertUserCharacter(@RequestBody UserCharacterEntity user){
        try{
            UserCharacterEntity insertedUserItem = userCharacterService.insertUserCharacter(user);
            return Response.response(HttpStatus.CREATED, "User Character inserted successfully!", insertedUserItem);
        }catch (IllegalArgumentException ex){
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }catch (Exception ex){
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }


    @GetMapping("get_user_character_by/{userCharacterID}")
    public ResponseEntity<Object> getUserCharacterById(@PathVariable int userCharacterID){
        try{
            Optional<UserCharacterEntity> userCharacter = userCharacterService.getUserCharacterById(userCharacterID);
            if(userCharacter.isPresent()){
                return Response.response(HttpStatus.OK, "User Character retrieved successfully!", userCharacter);
            }else {
                return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "User Character does not Exist!");
            }
        }catch (IllegalArgumentException ex){
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }catch (Exception ex){
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @GetMapping("get_all_user_characters")
    public ResponseEntity<Object> getAllUserCharacter() {
        try{
            List<UserCharacterEntity> allUserCharacters = userCharacterService.getAllUserCharacter();
            if (allUserCharacters != null && !allUserCharacters.isEmpty()){
                return Response.response(HttpStatus.OK, "All Existing UserCharacters retrieved successfully!", allUserCharacters);
            }else{
                return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "No UserCharacters found!");
            }
        }catch (IllegalArgumentException ex){
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }catch (Exception ex){
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }

    }

    @PatchMapping("update_user_character_is_owned/{userCharacterID}")
    public ResponseEntity<Object> updateUserCharacter (@PathVariable int userCharacterID, @RequestBody UserCharacterEntity newUserItemDetails){
        try{
            UserCharacterEntity updatedUserCharacter = userCharacterService.updateUserCharacter(userCharacterID, newUserItemDetails);
            if(updatedUserCharacter != null){
                return Response.response(HttpStatus.OK, "User character updated successfully", updatedUserCharacter);
            } else {
                return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "User character not found");
            }
        }catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }catch (Exception ex){
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @DeleteMapping("/delete_user_character/{userCharacterID}")
    public ResponseEntity<Object> deleteUserCharacter(int userCharacterID){
        try {
            String deletionResult = userCharacterService.deleteUserCharacter(userCharacterID);
            if (deletionResult.equals("User Character " + userCharacterID + " is successfully deleted!")) {
                return NoDataResponse.noDataResponse(HttpStatus.OK, deletionResult);
            } else {
                return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, deletionResult);
            }
        }catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }catch (Exception ex){
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @GetMapping("/get_user_character_id_by/{userID}/{characterID}")
    public ResponseEntity<Object> getUserCharacterIDByUserIDAndCharacterID(@PathVariable int userID, @PathVariable int characterID){
        try{
            Optional<Integer> userCharacterID = userCharacterService.getUserCharacterIDByUserIDAndCharacterID(userID, characterID);
            if(userCharacterID.isPresent()){
                return Response.response(HttpStatus.OK, "User character ID retrieved successfully", userCharacterID.get());
            } else {
                return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "User character ID not found!");
            }
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (Exception ex){
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @GetMapping("/get_user_character_by/{userID}/{characterID}")
    public ResponseEntity<Object> getUserCharacterByUserIDAndCharacterID(@PathVariable int userID, @PathVariable int characterID){
        try{
            Optional<UserCharacterEntity> userCharacter = userCharacterService.getUserCharacterByUserIDAndCharacterID(userID, characterID);
            if(userCharacter.isPresent()){
                return Response.response(HttpStatus.OK, "User character retrieved successfully", userCharacter.get());
            } else {
                return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "User character not found!");
            }
        }catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (Exception ex){
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @PostMapping("/buy_character_by/{userID}/{characterID}")
    public ResponseEntity<Object> buyCharacter(@PathVariable int userID, @PathVariable int characterID){
        try{
            String buyResult = userCharacterService.buyCharacter(userID, characterID);
            return NoDataResponse.noDataResponse(HttpStatus.OK, buyResult);
        } catch(InsufficientCreditException | UserCharacterAlreadyExistException | IllegalArgumentException ex){
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (Exception ex){
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @GetMapping("/get_user_characters_by/{userID}")
    public ResponseEntity<Object> getUserCharactersByUserID(@PathVariable int userID) {
        try{
            List<UserCharacterEntity> userCharacters = userCharacterService.getAllUserCharacterByUserID(userID);
            return Response.response(HttpStatus.OK, "User Characters owned by " + userID + " retrieved successfully!", userCharacters);
        }catch (IllegalArgumentException ex){
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }catch (Exception ex){
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @GetMapping("/equip_character_by/{userID}/{characterID}")
    public ResponseEntity<Object> equipCharacter(@PathVariable int userID, @PathVariable int characterID){
        try{
            String equipResult = userCharacterService.equipCharacter(userID, characterID);
            return NoDataResponse.noDataResponse(HttpStatus.OK, equipResult);
        }catch (UserCharacterNotFoundException | CharacterNotFoundException e){
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, e.getMessage());
        }catch (IllegalArgumentException | UserCharacterAlreadyExistException e){
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }catch (Exception ex){
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }
}
