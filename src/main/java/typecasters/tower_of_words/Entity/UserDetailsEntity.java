package typecasters.tower_of_words.Entity;

import jakarta.persistence.*;

@Entity
@Table(name ="tbl_user_details")
public class UserDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_detail_id")
    private int user_detail_id;
    private int userId;
    private int credit_amount ;
    private int words_collected ;
    private int achievement_count ;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tower_progress_id")
    private TowerProgressEntity userProgress;
//    @OneToMany(cascade = CascadeType.ALL)
//    private List<UserItemEntity> userItemList;


    public UserDetailsEntity() {
    }

    public UserDetailsEntity(int userId, int credit_amount, int words_collected, int achievement_count) {
        this.userId = userId;
        this.credit_amount = credit_amount;
        this.words_collected = words_collected;
        this.achievement_count = achievement_count;
    }

    public UserDetailsEntity(int userId, int credit_amount, int words_collected, int achievement_count, TowerProgressEntity userProgress) {
        this.userId = userId;
        this.credit_amount = credit_amount;
        this.words_collected = words_collected;
        this.achievement_count = achievement_count;
        this.userProgress = userProgress;
    }

    public UserDetailsEntity(int user_detail_id, int userId, int credit_amount, int words_collected, int achievement_count) {
        this.user_detail_id = user_detail_id;
        this.userId = userId;
        this.credit_amount = credit_amount;
        this.words_collected = words_collected;
        this.achievement_count = achievement_count;
    }

    public UserDetailsEntity(int user_detail_id, int userId, int credit_amount, int words_collected, int achievement_count, TowerProgressEntity userProgress) {
        this.user_detail_id = user_detail_id;
        this.userId = userId;
        this.credit_amount = credit_amount;
        this.words_collected = words_collected;
        this.achievement_count = achievement_count;
        this.userProgress = userProgress;
    }



    public TowerProgressEntity getUserProgress() {
        return userProgress;
    }

    public void setUserProgress(TowerProgressEntity userProgress) {
        this.userProgress = userProgress;
    }

    public int getUser_detail_id() {
        return user_detail_id;
    }

    public void setUser_detail_id(int user_detail_id) {
        this.user_detail_id = user_detail_id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCredit_amount() {
        return credit_amount;
    }

    public void setCredit_amount(int credit_amount) {
        this.credit_amount = credit_amount;
    }

    public int getWords_collected() {
        return words_collected;
    }

    public void setWords_collected(int words_collected) {
        this.words_collected = words_collected;
    }

    public int getAchievement_count() {
        return achievement_count;
    }

    public void setAchievement_count(int achievement_count) {
        this.achievement_count = achievement_count;
    }
}
