/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animelist.controller;

import animelist.model.AnimeDTO;
import animelist.model.AnimeListDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class SearchAction {

    private String searchValue;
    private String type;
    private int genreID;
    private int studioID;
    private int seasonID;
    private ArrayList<AnimeDTO> searchAnimeList;
    private final String SUCCESS = "succes";

    public SearchAction() {
    }

    public String execute() {
        try {
            AnimeListDAO dao = new AnimeListDAO();
            
            searchAnimeList = dao.getSearchAnime(searchValue, type, studioID, genreID, seasonID);
            
            return SUCCESS;
            
        } catch (SQLException ex) {
            Logger.getLogger(SearchAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getGenreID() {
        return genreID;
    }

    public void setGenreID(int genreID) {
        this.genreID = genreID;
    }

    public int getStudioID() {
        return studioID;
    }

    public void setStudioID(int studioID) {
        this.studioID = studioID;
    }

    public int getSeasonID() {
        return seasonID;
    }

    public void setSeasonID(int seasonID) {
        this.seasonID = seasonID;
    }

    public ArrayList<AnimeDTO> getSearchAnimeList() {
        return searchAnimeList;
    }

    public void setSearchAnimeList(ArrayList<AnimeDTO> searchAnimeList) {
        this.searchAnimeList = searchAnimeList;
    }
    
    

}
