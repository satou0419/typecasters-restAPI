package typecasters.tower_of_words;

public class UserInfoAndDetails {
    private int userDetailsId;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String userType;


    public UserInfoAndDetails() {
    }

    public UserInfoAndDetails(String userType, String email, String lastname, String firstname, String username, int userDetailsId) {
        this.userType = userType;
        this.email = email;
        this.lastname = lastname;
        this.firstname = firstname;
        this.username = username;
        this.userDetailsId = userDetailsId;
    }

    public int getUserDetailsId() {
        return userDetailsId;
    }

    public void setUserDetailsId(int userDetailsId) {
        this.userDetailsId = userDetailsId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
