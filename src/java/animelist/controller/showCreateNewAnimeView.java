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
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author HAPPY
 */
public class showCreateNewAnimeView extends ActionSupport implements ServletRequestAware {

    HttpServletRequest request;
    ArrayList<StudioDTO> studiosArrayList = new ArrayList<>();
    ArrayList<GenreDTO> genreArrayList = new ArrayList<>();
    ArrayList<SeasonDTO> seasonArrayList = new ArrayList<>();



    private final String FAIL = "fail";
    private final String SUCCESS = "success";

    public showCreateNewAnimeView() {
    }

    @Override
    public String execute() {
        try {
            AnimeListDAO dao = new AnimeListDAO();
            studiosArrayList = dao.getStudios();
            genreArrayList = dao.getGenres();
            seasonArrayList=(ArrayList<SeasonDTO>) dao.getSeasons();
            return SUCCESS;
        } catch (SQLException ex) {
            Logger.getLogger(showCreateNewAnimeView.class.getName()).log(Level.SEVERE, null, ex);
        }
        return FAIL;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public ArrayList<StudioDTO> getStudiosArrayList() {
        return studiosArrayList;
    }

    public void setStudiosArrayList(ArrayList<StudioDTO> studiosArrayList) {
        this.studiosArrayList = studiosArrayList;
    }

    public void setGenreArrayList(ArrayList<GenreDTO> genreArrayList) {
        this.genreArrayList = genreArrayList;
    }

    public ArrayList<GenreDTO> getGenreArrayList() {
        return genreArrayList;
    }
    public ArrayList<SeasonDTO> getSeasonArrayList() {
        return seasonArrayList;
    }

    public void setSeasonArrayList(ArrayList<SeasonDTO> seasonArrayList) {
        this.seasonArrayList = seasonArrayList;
    }
    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;//To change body of generated methods, choose Tools | Templates.
    }

}
