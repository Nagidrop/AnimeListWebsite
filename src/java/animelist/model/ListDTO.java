/**
 *
 * @author Wibu Group (Duc Tong, Duc Loc, Minh Thang, Tien Minh)
 */
package animelist.model;

import java.io.Serializable;

/* DTO for List table */
public class ListDTO implements Serializable {

    /* Anime List (for users) properties */
    private int animeID;
    private int accountID;
    private String status;
    private int progress;

    /* Constructors with and without arguments */
    public ListDTO() {
    }

    public ListDTO(int animeID, int accountID, String status, int progress) {
        this.animeID = animeID;
        this.accountID = accountID;
        this.status = status;
        this.progress = progress;
    }

    /* Getters and Setters */
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
    

}
