package typecasters.tower_of_words.Service;

import typecasters.tower_of_words.Entity.AdventureEnemyEntity;
import typecasters.tower_of_words.Repository.AdventureEnemyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AdventureEnemyService {

    @Autowired
    private AdventureEnemyRepository adventureEnemyRepository;

    //Create
    public AdventureEnemyEntity insertEnemy(AdventureEnemyEntity enemy){
        return adventureEnemyRepository.save(enemy);
    }

    //Read
    public List<AdventureEnemyEntity> getAllEnemy(){
        return adventureEnemyRepository.findAll();
    }

    public Optional<AdventureEnemyEntity> getEnemyById(int enemy_id){
        return adventureEnemyRepository.findById(enemy_id);
    }

    //Get All By TowerId

    public Optional<List<AdventureEnemyEntity>> getAllByFloorId(int tower_id){
        return adventureEnemyRepository.findAllByTowerFloorID(tower_id);
    }

    //Update
    @SuppressWarnings({"finally", "ReturnInsideFinallyBlock"})
    public AdventureEnemyEntity updateEnemy(int enemy_id, AdventureEnemyEntity newEnemyDetail){
        AdventureEnemyEntity enemy = new AdventureEnemyEntity();

        try{
            enemy = adventureEnemyRepository.findById(enemy_id).get();

            enemy.setImagePath(newEnemyDetail.getImagePath());
            enemy.setTowerFloorID(newEnemyDetail.getTowerFloorID());
            enemy.setWords(newEnemyDetail.getWords());
        }catch (NoSuchElementException ex){
            throw new NoSuchElementException("Enemy does not exist");
        }finally{
            return adventureEnemyRepository.save(enemy);
        }
    }

    //Delete
    public String deleteEnemy(int enemy_id){
        String msg = "Enemy id does not exist";

        if(adventureEnemyRepository.findById(enemy_id).isPresent()){
            adventureEnemyRepository.deleteById(enemy_id);
        }

        return msg;

    }


}
