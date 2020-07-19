/**
 *
 * @author Wibu Group (Duc Tong, Duc Loc, Minh Thang, Tien Minh)
 */
package animelist.model;

import java.io.Serializable;
import java.sql.Date;

/* DTO for Anime_Genre table */
public class AnimeGenreDTO implements Serializable {

    /* Anime_Genre properties */
    private int genreID;
    private int animeID;
    private Date created_at;
    private Date deleted_at;

    /* Constructors with and without arguments */
    public AnimeGenreDTO() {
    }

    public AnimeGenreDTO(int genreID, int animeID, Date created_at, Date deleted_at) {
        this.genreID = genreID;
        this.animeID = animeID;
        this.created_at = created_at;
        this.deleted_at = deleted_at;
    }

    /* Getters and Setters */
    public int getGenreID() {
        return genreID;
    }

    public void setGenreID(int genreID) {
        this.genreID = genreID;
    }

    public int getAnimeID() {
        return animeID;
    }

    public void setAnimeID(int animeID) {
        this.animeID = animeID;
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
