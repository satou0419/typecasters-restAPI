package typecasters.tower_of_words.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import typecasters.tower_of_words.Entity.CharacterEntity;
import typecasters.tower_of_words.Response.NoDataResponse;
import typecasters.tower_of_words.Response.Response;
import typecasters.tower_of_words.Service.CharacterService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/character")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @PostMapping("/add_character")
    public ResponseEntity<Object> insertCharacter(@RequestBody CharacterEntity character){
        try{
            CharacterEntity insertedCharacter = characterService.insertCharacter(character);
            return Response.response(HttpStatus.OK, "Character added succesfully", insertedCharacter);
        }catch (Exception e){
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to add character!");
        }
    }

    @GetMapping("/get_all_characters")
    public ResponseEntity<Object> getAllCharacters(){
        try{
            List<CharacterEntity> characters = characterService.getAllCharacters();
            return Response.response(HttpStatus.OK, "Characters retrieved succesfully", characters);
        }catch (Exception e){
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieved characters!");
        }
    }

    @GetMapping("/get_character_by_id/{characterID}")
    public ResponseEntity<Object> getCharacterByID(@PathVariable int characterID){
        try{
            Optional<CharacterEntity> character = characterService.getCharacterByID(characterID);
            return Response.response(HttpStatus.OK, "Character retrieved successfully!", character);
        }catch (NoSuchElementException e){
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, e.getMessage());
        }catch (Exception e){
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieved character");
        }
    }

    @PutMapping("/update_character/{characterID}")
    public ResponseEntity<Object> updateCharacter(@PathVariable int characterID,@RequestBody CharacterEntity newCharacterDetails) {

            try{
                CharacterEntity updatedCharacter = characterService.updateCharacter(characterID, newCharacterDetails);
                return Response.response(HttpStatus.OK, "Character updated successfully!", updatedCharacter);
            }catch (NoSuchElementException e){
                return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "Character not found!");
            }catch (Exception e){
                return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update the character " + characterID);
            }
    }

    @DeleteMapping("/delete_character/{characterID}")
    public ResponseEntity<Object> deleteCharacter(@PathVariable int characterID){
        try{
            String message = characterService.deleteCharacter(characterID);

            return NoDataResponse.noDataResponse(HttpStatus.OK, message);
        }catch(NoSuchElementException e){
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "Character by " + characterID + " is not found!");
        }catch (Exception e){
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to deleted character!");
        }
    }
}
