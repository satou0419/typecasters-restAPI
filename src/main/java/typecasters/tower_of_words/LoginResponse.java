package typecasters.tower_of_words;

import lombok.Getter;

public class LoginResponse {

    @Getter
    private int userId;
    private boolean isLoggedIn;

    public LoginResponse(int userId, boolean isLoggedIn) {
        this.userId = userId;
        this.isLoggedIn = isLoggedIn;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean getIsLoggedIn() {
        return isLoggedIn;
    }

    public void setIsLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }
}
