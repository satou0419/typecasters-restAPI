package com.typecasters.apitowerofwords.Service;

import com.typecasters.apitowerofwords.Entity.AdventureRewardEntity;
import com.typecasters.apitowerofwords.Entity.ItemEntity;
import com.typecasters.apitowerofwords.Entity.UserDetailsEntity;
import com.typecasters.apitowerofwords.Entity.UserItemEntity;
import com.typecasters.apitowerofwords.Exception.AdventureRewardNotFound;
import com.typecasters.apitowerofwords.Exception.InsufficientCreditException;
import com.typecasters.apitowerofwords.Exception.ItemQuantityUpdateException;
import com.typecasters.apitowerofwords.Repository.AdventureRewardRepository;
import com.typecasters.apitowerofwords.Repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AdventureRewardService {

    @Autowired
    private AdventureRewardRepository adventureRewardRepository;

    @Autowired
    private UserItemService userItemService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ItemService itemService;


    // Create
    public AdventureRewardEntity insertReward(AdventureRewardEntity reward) {
        return adventureRewardRepository.save(reward);
    }

    // Read
    public List<AdventureRewardEntity> getAllReward() {
        return adventureRewardRepository.findAll();
    }

    public Optional<AdventureRewardEntity> getRewardByTowerFloorId(int towerFloorId) {
        return adventureRewardRepository.findByTowerFloorId(towerFloorId);
    }

    // Update
    public AdventureRewardEntity updateReward(AdventureRewardEntity newRewardDetail, int rewardId) {
        try {
            AdventureRewardEntity reward = adventureRewardRepository.findById(rewardId).orElseThrow(() -> new NoSuchElementException("Reward does not exist"));
            reward.setAdvRewardCredit(newRewardDetail.getAdvRewardCredit());
            reward.setRewardItemOne(newRewardDetail.getRewardItemOne());
            reward.setRewardItemTwo(newRewardDetail.getRewardItemTwo());
            return adventureRewardRepository.save(reward);
        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("Reward does not exist");
        }
    }

    // Delete
    public String deleteReward(int rewardId) {
        if (adventureRewardRepository.findById(rewardId).isPresent()) {
            adventureRewardRepository.deleteById(rewardId);
            return "Reward deleted successfully";
        } else {
            return "Reward does not exist";
        }
    }

    // Updates userItemQuantity and userDetailCredit
    @Transactional
    public String updateUserCreditANDUserItem(int floorId, int userId){
        try{
            UserDetailsEntity userDetails = userDetailsService.getUserDetails(userId);
            Optional<AdventureRewardEntity> adventureRewardObject = getRewardByTowerFloorId(floorId);

            if(adventureRewardObject.isPresent()){
                AdventureRewardEntity adventureReward = adventureRewardObject.get();

                int rewardCredit = adventureReward.getAdvRewardCredit();
                int userCredit = userDetails.getCredit_amount();

                if(rewardCredit > 0){
                    userDetails.setCredit_amount(userCredit + rewardCredit);
                  //  adventureReward.getRewardItemOne().getRewardItemQuantity()
                    int rewardItemIdOne = adventureReward.getRewardItemOne().getRewardItem().getItemId();
                    int rewardItemQuantityOne = adventureReward.getRewardItemOne().getRewardItemQuantity();

                    int rewardItemIdTwo = adventureReward.getRewardItemTwo().getRewardItem().getItemId();
                    int rewardItemQuantityTwo = adventureReward.getRewardItemTwo().getRewardItemQuantity();

                    boolean updatedUserItemQuantityOne = updateUserItemQuantityByRewardItemQuantity(userId, rewardItemIdOne, rewardItemQuantityOne);
                    boolean updatedUserItemQuantityTwo = updateUserItemQuantityByRewardItemQuantity(userId, rewardItemIdTwo, rewardItemQuantityTwo);


                    if(updatedUserItemQuantityOne && updatedUserItemQuantityTwo){
                        return "The reward is successfully sent to the user!";
                    }else{
                        throw new ItemQuantityUpdateException("Failed to update user item quantity!");
                    }

                }else{
                    throw new InsufficientCreditException("The reward credit must be above 0!");
                }
            }else{
                throw new AdventureRewardNotFound("The reward for floor " + floorId + " does not exist!");
            }
        }catch(AdventureRewardNotFound ex){
            return ex.getMessage();
        }catch(InsufficientCreditException ex){
            return ex.getMessage();
        }catch(ItemQuantityUpdateException ex){
            return ex.getMessage();
        }

    }

    // Create a function class that updates the UserItem Quantity based on the RewardItem Quantity
    @Transactional
    private boolean updateUserItemQuantityByRewardItemQuantity(int userId, int itemId, int rewardItemQuantity){

        try{

            Optional <UserItemEntity> userItemObject = userItemService.getUserItemByUserIdAndItemId(userId, itemId);
            ItemEntity item = itemService.getItem(itemId);

            if(userItemObject.isPresent()){
                UserItemEntity userItem = userItemObject.get();

                int newQuantity = userItem.getQuantity() + rewardItemQuantity;

                userItem.setQuantity(newQuantity);
            }else{
                UserItemEntity userItem = new UserItemEntity(rewardItemQuantity, userId, item);
                userItemService.insertUserItem(userItem);
            }

            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
