/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animelist.controller;

import animelist.model.AnimeListDAO;
import animelist.model.GenreDTO;
import animelist.model.StudioDTO;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author HAPPY
 */
public class CreateNewAnimeAction extends ActionSupport implements ServletRequestAware {

    HttpServletRequest request;
    private final String FAIL = "fail";
    private final String SUCCESS = "success";

    public CreateNewAnimeAction() {

    }

    @Override
    public String execute() {
        try {
            AnimeListDAO dao = new AnimeListDAO();
            String name = request.getParameter("name");
            String type = request.getParameter("type");
            String releaseDate = request.getParameter("releaseDate");
            String rating = request.getParameter("rating");
            String episodes = request.getParameter("episodes");
            String status = request.getParameter("status");
            String duration = request.getParameter("duration");
            String description = request.getParameter("description");
            String trailer = request.getParameter("trailer");
            String season = request.getParameter("season");
            int id = dao.createNewAnime(1, season, type, name, releaseDate, rating, episodes, status, duration, description, "", trailer, null, null);
            String[] studio = request.getParameterValues("studio");
            for (String i : studio) {
                dao.createAnimeStudio(String.valueOf(id), i, null);
            }
            String[] genre = request.getParameterValues("genre");
            for (String i : genre) {
                dao.createAnimeGenre(String.valueOf(id), i, null);
            }
            return SUCCESS;
        } catch (SQLException ex) {
            Logger.getLogger(CreateNewAnimeAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return FAIL;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr; //To change body of generated methods, choose Tools | Templates.
    }

}
