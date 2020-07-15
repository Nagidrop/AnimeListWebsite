package animelist.model;

import java.io.Serializable;

/**
 *
 * @author Quan Duc Loc CE140037 (SE1401)
 */
public class ListDTO implements Serializable {
    private int animeId;
    private int accountId;
    private int status;
    private int progress;
    private int favourite;

    public ListDTO() {
    }

    public ListDTO(int animeId, int accountId, int status, int progress, int favourite) {
        this.animeId = animeId;
        this.accountId = accountId;
        this.status = status;
        this.progress = progress;
        this.favourite = favourite;
    }

    public int getAnimeId() {
        return animeId;
    }

    public void setAnimeId(int animeId) {
        this.animeId = animeId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getFavourite() {
        return favourite;
    }

    public void setFavourite(int favourite) {
        this.favourite = favourite;
    }
    
}
