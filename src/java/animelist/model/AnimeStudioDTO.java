package animelist.model;

import java.io.Serializable;

/**
 *
 * @author Quan Duc Loc CE140037 (SE1401)
 */
public class AnimeStudioDTO implements Serializable{
    private int animeID;
    private int studioID;

    public AnimeStudioDTO() {
    }

    public AnimeStudioDTO(int animeID, int studioID) {
        this.animeID = animeID;
        this.studioID = studioID;
    }

    public int getAnimeID() {
        return animeID;
    }

    public void setAnimeID(int animeID) {
        this.animeID = animeID;
    }

    public int getStudioID() {
        return studioID;
    }

    public void setStudioID(int studioID) {
        this.studioID = studioID;
    }
    
    
}
