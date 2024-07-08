package typecasters.tower_of_words.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import typecasters.tower_of_words.Entity.ArchiveWordsEntity;
import typecasters.tower_of_words.Exception.UserIdNotFoundException;
import typecasters.tower_of_words.Response.NoDataResponse;
import typecasters.tower_of_words.Response.Response;
import typecasters.tower_of_words.Service.ArchiveWordsService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/archive_words")
public class ArchiveWordsController {
    @Autowired
    ArchiveWordsService archiveWordsService;

    // Create
    @PostMapping("/insert/{userID}/{word}")
    public ResponseEntity<Object> insertArchiveWords(@PathVariable int userID, @PathVariable String word) {
        try {
            ArchiveWordsEntity insertedWord = archiveWordsService.insertArchiveWords(userID, word);
            return NoDataResponse.noDataResponse(HttpStatus.OK, "Word Archived!");
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (NoSuchElementException | NullPointerException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    // Read
    @GetMapping("/view/{userID}")
    public ResponseEntity<Object> viewAllArchiveWords(@PathVariable int userID) {
        try {
            List<ArchiveWordsEntity> words = archiveWordsService.viewAllArchiveWords(userID);
            return Response.response(HttpStatus.OK, "Words retrieved successfully", words);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (UserIdNotFoundException | NoSuchElementException | NullPointerException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @GetMapping("/view_by_id/{archiveWordsID}")
    public ResponseEntity<Object> viewArchiveWordsByID(@PathVariable int archiveWordsID) {
        try {
            Optional<ArchiveWordsEntity> word = archiveWordsService.viewArchiveWordsByID(archiveWordsID);
            return word.map(value -> Response.response(HttpStatus.OK, "Word retrieved successfully", value))
                    .orElseGet(() -> NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "Word not found"));
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (NoSuchElementException | NullPointerException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    // Update
    @PutMapping("/edit")
    public ResponseEntity<Object> editArchiveWords(@RequestBody ArchiveWordsEntity word) {
        try {
            ArchiveWordsEntity updatedWord = archiveWordsService.editArchiveWords(word);
            return Response.response(HttpStatus.OK, "Word updated successfully", updatedWord);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (NoSuchElementException | NullPointerException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    // Delete
    @PutMapping("/remove/{archiveWordsID}")
    public ResponseEntity<Object> removeArchiveWords(@PathVariable int archiveWordsID) {
        try {
            archiveWordsService.removeArchiveWords(archiveWordsID);
            return NoDataResponse.noDataResponse(HttpStatus.OK, "Word removed successfully");
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (NoSuchElementException | NullPointerException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }
}
