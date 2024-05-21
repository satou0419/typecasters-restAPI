package typecasters.tower_of_words.Controller;

import typecasters.tower_of_words.Entity.ItemEntity;
import typecasters.tower_of_words.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemServ;

    // Insert
    @PostMapping("/add_item")
    public ItemEntity insertItem(@RequestBody ItemEntity item){
        return itemServ.insertItem(item);
    }

    // Read All
    @GetMapping("/get_all_items")
    public List<ItemEntity> getAllItems() {
        return itemServ.getAllItems();
    }

    // Read One
    @GetMapping("/get_item_by_id/{itemId}")
    public ItemEntity getItem(@PathVariable int itemId){
        return itemServ.getItem(itemId);
    }
    // Update
    @PutMapping("/update_item/{item_id}")
    public ItemEntity updateItem(@PathVariable int item_id, @RequestBody ItemEntity new_item_details){
        return itemServ.updateItem(item_id, new_item_details);
    }

    // Delete
    @DeleteMapping("/delete_item/{item_id}")
    public String deleteItem(@PathVariable int item_id) {
        return itemServ.deleteItem(item_id);
    }

}
