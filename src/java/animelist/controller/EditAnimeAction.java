/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animelist.controller;

import animelist.model.AnimeDTO;
import animelist.model.AnimeListDAO;
import animelist.model.GenreDTO;
import animelist.model.SeasonDTO;
import animelist.model.StudioDTO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author HAPPY
 */
public class EditAnimeAction extends ActionSupport implements ServletRequestAware {

    HttpServletRequest request;
    ArrayList<StudioDTO> studiosArrayList = new ArrayList<>();
    ArrayList<GenreDTO> genreArrayList = new ArrayList<>();
    ArrayList<SeasonDTO> seasonArrayList = new ArrayList<>();
    private final String FAIL = "fail";
    private final String SUCCESS = "success";
    AnimeDTO anime = new AnimeDTO();

    public EditAnimeAction() {
    }

    @Override
    public String execute() {
        try {
            AnimeListDAO dao = new AnimeListDAO();
            Map session = ActionContext.getContext().getSession();
            if (session.isEmpty()) {
                return FAIL;
            }

            int roleID = (int) session.get("roleid");
            if (roleID != 1) {
                return FAIL;
            }
            studiosArrayList = dao.getStudios();
            genreArrayList = dao.getGenres();
            seasonArrayList = (ArrayList<SeasonDTO>) dao.getSeasons();
            int id = Integer.parseInt(request.getParameter("id"));
            anime = dao.getAnimeDetails(id);
            return SUCCESS;
        } catch (SQLException ex) {
            Logger.getLogger(EditAnimeAction.class.getName()).log(Level.SEVERE, null, ex);
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

    public ArrayList<GenreDTO> getGenreArrayList() {
        return genreArrayList;
    }

    public void setGenreArrayList(ArrayList<GenreDTO> genreArrayList) {
        this.genreArrayList = genreArrayList;
    }

    public ArrayList<SeasonDTO> getSeasonArrayList() {
        return seasonArrayList;
    }

    public void setSeasonArrayList(ArrayList<SeasonDTO> seasonArrayList) {
        this.seasonArrayList = seasonArrayList;
    }

    public AnimeDTO getAnime() {
        return anime;
    }

    public void setAnime(AnimeDTO anime) {
        this.anime = anime;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

}
