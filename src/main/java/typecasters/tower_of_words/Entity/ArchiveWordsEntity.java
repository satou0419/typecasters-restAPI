package typecasters.tower_of_words.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_archive_words")
public class ArchiveWordsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int archiveWordsID;

    private int userID;

    private String word;

    private boolean isChecked = false;

}
