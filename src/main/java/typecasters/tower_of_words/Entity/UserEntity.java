package typecasters.tower_of_words.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import typecasters.tower_of_words.FieldConverter;

import jakarta.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;

    private String firstname;

    private String lastname;

    @Column(unique = true)
    private String username;

    @Convert(converter = FieldConverter.class)
    private String password;

    private String userType;

    private String email;

    private Boolean isLoggedIn = false;
}
