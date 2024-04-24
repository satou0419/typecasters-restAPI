package com.typecasters.apitowerofwords.Service;

import com.typecasters.apitowerofwords.Entity.ItemEntity;
import com.typecasters.apitowerofwords.Entity.UserEntity;
import com.typecasters.apitowerofwords.Entity.UserItemEntity;
import com.typecasters.apitowerofwords.Repository.ItemRepository;
import com.typecasters.apitowerofwords.Repository.UserItemRepository;
import com.typecasters.apitowerofwords.Repository.UserRepository;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository item_repo;

    // Create
    public ItemEntity insertItem(ItemEntity item) {
        return item_repo.save(item);
    }


    // Read
    public List<ItemEntity> getAllItems() {
        return item_repo.findAll();
    }

    public Optional<ItemEntity> getItemById(int item_id) {
        return item_repo.findById(item_id);
    }

    // Update
    @SuppressWarnings({"finally", "ReturnInsideFinallyBlock"})
    public ItemEntity updateItem(int item_id, ItemEntity new_item_details){
        ItemEntity item = new ItemEntity();

        try {
            item = item_repo.findById(item_id).get();

            item.setItem_name(new_item_details.getItem_name());
            item.setItem_description(new_item_details.getItem_description());
            item.setItem_price(new_item_details.getItem_price());
            item.setImage_path(new_item_details.getImage_path());
        }catch (NoSuchElementException ex) {
            throw new NoSuchElementException("Item " + item_id + " does not exist!");
        }finally {
            return item_repo.save(item);
        }
    }


    // Delete
    public String deleteItem(int item_id) {
        String msg = "";

        if(item_repo.findById(item_id).isPresent()) {
            item_repo.deleteById(item_id);

            msg = "Item " + item_id + " is successfully deleted!";
        }

        return msg;
    }


}
