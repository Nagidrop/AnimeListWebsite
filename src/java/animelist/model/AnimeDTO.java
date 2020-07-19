/**
 *
 * @author Wibu Group (Duc Tong, Duc Loc, Minh Thang, Tien Minh)
 */
package animelist.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

/* DTO for Anime table */
public class AnimeDTO implements Serializable {

    /* An Anime properties */
    private int id;
    private int accountID;
    private String season;
    private ArrayList<StudioDTO> studios;
    private ArrayList<GenreDTO> genres;
    private String type;
    private String name;
    private Date releaseDate;
    private String rating;
    private int episodes;
    private String status;
    private String duration;
    private String description;
    private String poster;
    private String trailer;
    private Date created_at;
    private Date deleted_at;

    /* Constructors with and without arguments */
    public AnimeDTO() {
    }

    public AnimeDTO(int id, int accountID, String season, ArrayList<StudioDTO> studios, ArrayList<GenreDTO> genres, String type, String name, Date releaseDate, String rating, int episodes, String status, String duration, String description, String poster, String trailer, Date created_at, Date deleted_at) {
        this.id = id;
        this.accountID = accountID;
        this.season = season;
        this.studios = studios;
        this.genres = genres;
        this.type = type;
        this.name = name;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.episodes = episodes;
        this.status = status;
        this.duration = duration;
        this.description = description;
        this.poster = poster;
        this.trailer = trailer;
        this.created_at = created_at;
        this.deleted_at = deleted_at;
    }

    /* Getters and Setters */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public ArrayList<StudioDTO> getStudios() {
        return studios;
    }

    public void setStudios(ArrayList<StudioDTO> studios) {
        this.studios = studios;
    }

    public ArrayList<GenreDTO> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<GenreDTO> genres) {
        this.genres = genres;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
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
