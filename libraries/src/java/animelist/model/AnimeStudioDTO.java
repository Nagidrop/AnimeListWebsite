/**
 *
 * @author Wibu Group (Duc Tong, Duc Loc, Minh Thang, Tien Minh)
 */
package animelist.model;

import java.io.Serializable;

/* DTO for Anime_Studio table */
public class AnimeStudioDTO implements Serializable {

    /* Anime_Studio properties */
    private int animeID;
    private int studioID;

    /* Constructors with and without arguments */
    public AnimeStudioDTO() {
    }

    public AnimeStudioDTO(int animeID, int studioID) {
        this.animeID = animeID;
        this.studioID = studioID;
    }

    /* Getters and Setters */
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
