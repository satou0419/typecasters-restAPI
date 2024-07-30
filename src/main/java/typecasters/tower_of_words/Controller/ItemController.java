package typecasters.tower_of_words.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import typecasters.tower_of_words.Entity.ItemEntity;
import typecasters.tower_of_words.Response.NoDataResponse;
import typecasters.tower_of_words.Response.Response;
import typecasters.tower_of_words.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemServ;

    // Insert
    @PostMapping("/add_item")
    public ResponseEntity<Object> insertItem(@RequestBody ItemEntity item) {
        try {
            ItemEntity insertedItem = itemServ.insertItem(item);
            return Response.response(HttpStatus.OK, "Item added successfully", insertedItem);
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to add item");
        }
    }

    // Read All
    @GetMapping("/get_all_items")
    public ResponseEntity<Object> getAllItems() {
        try {
            List<ItemEntity> items = itemServ.getAllItems();
            return Response.response(HttpStatus.OK, "Items retrieved successfully", items);
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve items");
        }
    }

    // Read One
    @GetMapping("/get_item_by_id/{itemID}")
    public ResponseEntity<Object> getItem(@PathVariable int itemID) {
        try {
            ItemEntity item = itemServ.getItem(itemID);
            return Response.response(HttpStatus.OK, "Item retrieved successfully", item);
        } catch (NoSuchElementException e) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "Item not found");
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve item");
        }
    }

    // Update
    @PutMapping("/update_item/{itemID}")
    public ResponseEntity<Object> updateItem(@PathVariable int itemID, @RequestBody ItemEntity newItemDetails) {
        try {
            ItemEntity updatedItem = itemServ.updateItem(itemID, newItemDetails);
            return Response.response(HttpStatus.OK, "Item updated successfully", updatedItem);
        } catch (NoSuchElementException e) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "Item not found");
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update item");
        }
    }

    // Delete
    @DeleteMapping("/delete_item/{itemID}")
    public ResponseEntity<Object> deleteItem(@PathVariable int itemID) {
        try {
            String message = itemServ.deleteItem(itemID);
            return NoDataResponse.noDataResponse(HttpStatus.OK, message);
        } catch (NoSuchElementException e) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "Item not found");
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete item");
        }
    }

}
