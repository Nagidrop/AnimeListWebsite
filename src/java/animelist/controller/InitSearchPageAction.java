/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

/**
 *
 * @author PC
 */
public class InitSearchPageAction {

    private ArrayList<String> types;
    private ArrayList<GenreDTO> genres;
    private ArrayList<StudioDTO> studios;
    private List<SeasonDTO> seasons;
    private final String SUCCESS = "success";

    public InitSearchPageAction() {
    }

    public String execute() {

        try {
            AnimeListDAO dao = new AnimeListDAO();

            types = dao.getTypes();
            genres = dao.getGenres();
            studios = dao.getStudios();
            seasons = dao.getSeasons();

        } catch (SQLException ex) {
            Logger.getLogger(InitSearchPageAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        return SUCCESS;
    }

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
