package typecasters.tower_of_words.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import typecasters.tower_of_words.Entity.UserCharacterEntity;
import typecasters.tower_of_words.Repository.UserCharacterRepository;

@Service
public class UserCharacterService {
    @Autowired
    UserCharacterRepository userCharacterRepository;

    public UserCharacterEntity insertUserCharacter(UserCharacterEntity user){
        return userCharacterRepository.save(user);
    }
}
