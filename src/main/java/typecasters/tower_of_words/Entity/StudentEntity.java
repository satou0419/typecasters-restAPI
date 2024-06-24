package typecasters.tower_of_words.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_student")
public class StudentEntity {
    @Id
    private Long id;
    private int sid;
    private String fname;
    private String lname;
    private String gender;
}
