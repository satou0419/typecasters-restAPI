package typecasters.tower_of_words.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import typecasters.tower_of_words.Repository.CharacterRepository;

@Service
public class CharacterService {
    @Autowired
    CharacterRepository characterRepository;
}
