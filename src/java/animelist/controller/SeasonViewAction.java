/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animelist.controller;

import animelist.model.AnimeListDAO;
import animelist.model.SeasonDTO;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author PC
 */
public class SeasonViewAction extends ActionSupport implements ServletRequestAware {
    HttpServletRequest request;
    private List<SeasonDTO> seasons;
    private final String FAIL = "fail";
    private final String SUCCESS = "success";

    public SeasonViewAction() {
    }

    @Override
    public String execute() throws Exception {
        AnimeListDAO dao = new AnimeListDAO();
        seasons = dao.getSeasons();
        request.setAttribute("listSeason", seasons);
        return SUCCESS;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    public List<SeasonDTO> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<SeasonDTO> seasons) {
        this.seasons = seasons;
    }

}
