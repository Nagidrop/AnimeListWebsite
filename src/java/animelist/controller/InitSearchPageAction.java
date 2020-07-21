/**
 *
 * @author Wibu Group (Duc Tong, Duc Loc, Minh Thang, Tien Minh)
 */

package animelist.controller;

import animelist.model.AnimeListDAO;
import animelist.model.GenreDTO;
import animelist.model.SeasonDTO;
import animelist.model.StudioDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* This action got called when an advanced search page opens up (before pressing search in advanced search page) */
public class InitSearchPageAction {

    /* Advanced Search Page crtieria to display to choose */
    private ArrayList<String> types;
    private ArrayList<GenreDTO> genres;
    private ArrayList<StudioDTO> studios;
    private List<SeasonDTO> seasons;
    private final String SUCCESS = "success"; // indicates sucessful action

    /* Constructor */
    public InitSearchPageAction() {
    }

    public String execute() {
        try {
            /* Instantiate DAO object and interacts with DB */
            AnimeListDAO dao = new AnimeListDAO();

            /* Get data from Database */
            types = dao.getTypes();
            genres = dao.getGenres();
            studios = dao.getStudios();
            seasons = dao.getSeasons();
        } catch (SQLException ex) {
            Logger.getLogger(InitSearchPageAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        return SUCCESS;
    }

    /* Getters and Setters */
    public ArrayList<String> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<String> types) {
        this.types = types;
    }

    public ArrayList<GenreDTO> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<GenreDTO> genres) {
        this.genres = genres;
    }

    public ArrayList<StudioDTO> getStudios() {
        return studios;
    }

    public void setStudios(ArrayList<StudioDTO> studios) {
        this.studios = studios;
    }

    public List<SeasonDTO> getSeasons() {
        return seasons;
    }

    public void setSeasons(ArrayList<SeasonDTO> seasons) {
        this.seasons = seasons;
    }

}
