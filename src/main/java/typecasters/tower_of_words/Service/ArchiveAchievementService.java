package typecasters.tower_of_words.Service;

import typecasters.tower_of_words.Entity.ArchiveAchievementEntity;
import typecasters.tower_of_words.Repository.ArchiveAchievementRepository;
import typecasters.tower_of_words.Repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ArchiveAchievementService {
    @Autowired
    ArchiveAchievementRepository archiveAchievementRepository;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    AchievementService achievementService;

    public ArchiveAchievementEntity insertArchiveAchievement(ArchiveAchievementEntity archiveAchievement){
        return archiveAchievementRepository.save(archiveAchievement);
    }

    public List<ArchiveAchievementEntity> getAllArchiveAchievement(){
        return archiveAchievementRepository.findAll();
    }

}
