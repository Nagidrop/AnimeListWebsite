package animelist.model;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Quan Duc Loc CE140037 (SE1401)
 */
public class ReviewDTO implements Serializable{
    private long id;
    private int animeID;
    private int accountID;
    private int score;
    private String content;
    private Date created_at;
    private Date deleted_at;

    public ReviewDTO() {
    }

    public ReviewDTO(long reviewID, int animeID, int accountID, int score, String content, Date created_at, Date deleted_at) {
        this.id = reviewID;
        this.animeID = animeID;
        this.accountID = accountID;
        this.score = score;
        this.content = content;
        this.created_at = created_at;
        this.deleted_at = deleted_at;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAnimeID() {
        return animeID;
    }

    public void setAnimeID(int animeID) {
        this.animeID = animeID;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Date deleted_at) {
        this.deleted_at = deleted_at;
    }
    
    
}
