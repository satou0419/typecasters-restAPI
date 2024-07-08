package typecasters.tower_of_words.Service;

import typecasters.tower_of_words.Entity.ItemEntity;
import typecasters.tower_of_words.Repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    // Create
    public ItemEntity insertItem(ItemEntity item) {
        return itemRepository.save(item);
    }

    // Read
    public List<ItemEntity> getAllItems() {
        return itemRepository.findAll();
    }

//    public Optional<ItemEntity> getItemById(int item_id) {
//        return item_repo.findById(item_id);
//    }

    public String getItemName(int itemId){
        return itemRepository.findNameByItemID(itemId);
    }

    public ItemEntity getItem(int itemId){
        return itemRepository.findOneByItemID(itemId);
    }

    // Update
    @SuppressWarnings({"finally", "ReturnInsideFinallyBlock"})
    public ItemEntity updateItem(int item_id, ItemEntity new_item_details){
        ItemEntity item = new ItemEntity();

        try {
            item = itemRepository.findById(item_id).get();

            item.setName(new_item_details.getName());
            item.setDescription(new_item_details.getDescription());
            item.setPrice(new_item_details.getPrice());
            item.setImagePath(new_item_details.getImagePath());
        }catch (NoSuchElementException ex) {
            throw new NoSuchElementException("Item " + item_id + " does not exist!");
        }finally {
            return itemRepository.save(item);
        }
    }


    // Delete
    public String deleteItem(int item_id) {
        String msg = "";

        if(itemRepository.findById(item_id).isPresent()) {
            itemRepository.deleteById(item_id);

            msg = "Item " + item_id + " is successfully deleted!";
        }

        return msg;
    }
}
