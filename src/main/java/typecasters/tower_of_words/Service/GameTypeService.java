package typecasters.tower_of_words.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import typecasters.tower_of_words.Entity.GameTypeEntity;
import typecasters.tower_of_words.Entity.ItemEntity;
import typecasters.tower_of_words.Repository.GameTypeRepository;

import java.util.List;
import java.util.NoSuchElementException;

@SuppressWarnings("ALL")
@Service
public class GameTypeService {

    @Autowired
    private GameTypeRepository gameTypeRepository;

    public GameTypeEntity insertGameType(GameTypeEntity game_type){
        return gameTypeRepository.save(game_type);
    }

    public List<GameTypeEntity> getAllGameTypes() {
        return gameTypeRepository.findAll();
    }

    public GameTypeEntity getGameType(int game_type_id){
        return gameTypeRepository.findOneByGameTypeID(game_type_id);
    }

    public GameTypeEntity updateGameType(int game_type_id, GameTypeEntity new_game_type_details){
        GameTypeEntity gameType = new GameTypeEntity();

        try{
            gameType = gameTypeRepository.findById(game_type_id).get();

            gameType.setGameTypeName(new_game_type_details.getGameTypeName());
            gameType.setGameTypeDescription(new_game_type_details.getGameTypeDescription());
        }catch(NoSuchElementException ex){
            throw new NoSuchElementException("GameType " + game_type_id + " does not exist!");
        }finally {
            return gameTypeRepository.save(gameType);
        }
    }

    public String deleteGameType(int game_type_id){
        String msg = "";

        if(gameTypeRepository.findById(game_type_id).isPresent()) {
            gameTypeRepository.deleteById(game_type_id);

            msg = "Item " + game_type_id + " is successfully deleted!";
        }

        return msg;
    }

}

