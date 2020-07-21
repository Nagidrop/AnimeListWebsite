/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animelist.controller;

import animelist.model.AnimeDTO;
import animelist.model.AnimeListDAO;
import animelist.model.StudioDTO;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author HAPPY
 */
public class ShowListAnimeAction extends ActionSupport implements ServletRequestAware {

    HttpServletRequest request;

    private final String FAIL = "fail";
    private final String SUCCESS = "success";
    private ArrayList<AnimeDTO> listAnimeDTOs = new ArrayList<>();
    private String StudioString="--Studio--";

    public String getStudioString() {
        return StudioString;
    }

    public void setStudioString(String StudioString) {
        this.StudioString = StudioString;
    }

    public ShowListAnimeAction() {
    }

    @Override
    public String execute() {
        try {
            AnimeListDAO dao = new AnimeListDAO();
            listAnimeDTOs = dao.getAllAnimes();
            ArrayList<StudioDTO> studios = dao.getStudios();
            ArrayList<String> StudioList = new ArrayList<>();
            System.out.println(studios.toArray());
            for (StudioDTO itemDTO : studios) {
                 StudioString +=","+ itemDTO.getName();
            }

            return SUCCESS;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ShowListAnimeAction.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return FAIL;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public ArrayList<AnimeDTO> getListAnimeDTOs() {
        return listAnimeDTOs;
    }

    public void setListAnimeDTOs(ArrayList<AnimeDTO> listAnimeDTOs) {
        this.listAnimeDTOs = listAnimeDTOs;
    }

    public static Logger getLOG() {
        return LOG;
    }

    public static void setLOG(Logger LOG) {
        ActionSupport.LOG = LOG;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

}
