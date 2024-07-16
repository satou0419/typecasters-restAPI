package typecasters.tower_of_words.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import typecasters.tower_of_words.Entity.CharacterEntity;
import typecasters.tower_of_words.Repository.CharacterRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CharacterService {
    @Autowired
    CharacterRepository characterRepository;

    public CharacterEntity insertCharacter(CharacterEntity character){
        return characterRepository.save(character);
    }

    public List<CharacterEntity> getAllCharacters(){
        return characterRepository.findAll();
    }

    public Optional<CharacterEntity> getCharacterByID(int characterID){
        return characterRepository.findById(characterID);
    }

    @SuppressWarnings({"finally", "ReturnInsideFinallyBlock"})
    public CharacterEntity updateCharacter(int characterID, CharacterEntity newCharacterDetails){
        CharacterEntity character = new CharacterEntity();

        try{
            character = getCharacterByID(characterID).get();

            character.setName(newCharacterDetails.getName());
            character.setImagePath(newCharacterDetails.getImagePath());
            character.setDescription(newCharacterDetails.getDescription());
            character.setPrice(newCharacterDetails.getPrice());
        }catch(NoSuchElementException ex){
            throw new NoSuchElementException("Character "+ characterID + " does not exist!");
        }finally{
            return  characterRepository.save(character);
        }
    }

    public String deleteCharacter(int characterID){
        String msg = "";

        if(getCharacterByID(characterID).isPresent()){
            characterRepository.deleteById(characterID);

            msg = "Character " + characterID + " is successfully deleted!";
        }

        return msg;
    }
}
