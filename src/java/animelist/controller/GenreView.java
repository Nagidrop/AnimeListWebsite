/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animelist.controller;

import animelist.model.AnimeListDAO;
import animelist.model.GenreDTO;
import animelist.model.SeasonDTO;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author PC
 */
public class GenreView extends ActionSupport implements ServletRequestAware {

    HttpServletRequest request;
    private ArrayList<GenreDTO> genres;
    private final String FAIL = "fail";
    private final String SUCCESS = "success";

    public GenreView() {
    }

    @Override
    public String execute() throws Exception {
        AnimeListDAO dao = new AnimeListDAO();
        genres = dao.getGenres();
        request.setAttribute("listGenre", genres);
        return SUCCESS;

    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public ArrayList<GenreDTO> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<GenreDTO> genres) {
        this.genres = genres;
    }

}
